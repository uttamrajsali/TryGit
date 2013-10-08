package com.nuance;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.lang.reflect.Method;
 
public class ReadXMLFile {
 
  public static void main(String args[]) {
 
    try {
   	//Login logintest = new Login();
    //logintest.execute();
    File fXmlFile = new File("testcases.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());//company
 
	NodeList nList = doc.getElementsByTagName("classname");
	
	System.out.println("----------------------------");
 
	/*?Class cls = Class.forName(name);
	Object obj = cls.newInstance();
	Class noparams[] = {};*/
	//This for loop execute only twice one for id = select time range another for id = timestep
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
			Element eElement = (Element) nNode;
			NodeList tLists = eElement.getElementsByTagName("testname");
			for (int j = 0; j < tLists.getLength(); j++)
		    {
		        Element x = (Element) tLists.item(j);
			System.out.println(x.getFirstChild().getNodeValue());
			//Method method = cls.getDeclaredMethod(x, noparams);
			//method.invoke(obj, null);
		    }
		}
	}
	
	
    } catch (Exception e) {
    	System.out.println("trace:");
	e.printStackTrace();
    }
  }
 
}