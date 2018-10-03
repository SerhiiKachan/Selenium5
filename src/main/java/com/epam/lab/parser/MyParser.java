package com.epam.lab.parser;

import com.epam.lab.parser.XML_models.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.epam.lab.constants.Constants.USER;
import static com.epam.lab.constants.Constants.EMAIL;
import static com.epam.lab.constants.Constants.PASSWORD;

public class MyParser {

    public Object[] parseXML(String path) {
        List<User> users = new ArrayList<>();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
            NodeList usersNodeList = document.getElementsByTagName(USER);
            for (int i = 0; i < usersNodeList.getLength(); i++) {
                Element singleUser = (Element) usersNodeList.item(i);
                users.add(new User.UserBuilder()
                        .setEmail(singleUser.getElementsByTagName(EMAIL).item(0).getChildNodes().item(0).getNodeValue())
                        .setPassword(singleUser.getElementsByTagName(PASSWORD).item(0).getChildNodes().item(0).getNodeValue())
                        .build());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return users.toArray();
    }

    public Properties parsePropertiesFile(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
