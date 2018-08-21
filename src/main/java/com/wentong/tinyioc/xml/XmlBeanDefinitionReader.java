package com.wentong.tinyioc.xml;

import com.wentong.tinyioc.AbstractBeanDefinitionReader;
import com.wentong.tinyioc.BeanDefinition;
import com.wentong.tinyioc.BeanReference;
import com.wentong.tinyioc.PropertyValue;
import com.wentong.tinyioc.io.ResourceLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlBeanDefinitionReader.class);

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinition(String location) {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinition(inputStream);
    }

    private void doLoadBeanDefinition(InputStream inputStream) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);
            registerBeanDefinitions(doc);
            inputStream.close();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("解析 xml 失败，失败原因：{}", e);
            throw new IllegalStateException("xml 解析失败，原因：" + e.getMessage());
        }
    }

    private void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element) {
                Element ele = (Element) item;
                processBeanDefinition(ele);
            }
        }
    }

    private void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 处理属性值
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList property = ele.getElementsByTagName("property");
        for (int i = 0; i < property.getLength(); i++) {
            Node item = property.item(i);
            if (item instanceof Element) {
                Element element = (Element) item;
                String name = element.getAttribute("name");
                String value = element.getAttribute("value");
                if (StringUtils.isNotBlank(name)) {
                    if (StringUtils.isNotBlank(value)) {
                        beanDefinition.getPropertyValues().addProperty(new PropertyValue(name, value));
                    } else {
                        String ref = element.getAttribute("ref");
                        if (StringUtils.isNotBlank(ref)) {
                            BeanReference beanReference = new BeanReference(ref);
                            beanDefinition.getPropertyValues().addProperty(new PropertyValue(ref,beanReference));
                        } else {
                            throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                    + name + "' must specify a ref or value");
                        }
                    }
                }
            }
        }
    }
}
