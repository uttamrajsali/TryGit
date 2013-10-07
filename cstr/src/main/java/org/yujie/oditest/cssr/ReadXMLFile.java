package org.yujie.oditest.cssr;

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
   	Login logintest = new Login();
    logintest.execute();
    File fXmlFile = new File("staff.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	Class cls = Class.forName("org.yujie.oditest.cssr.SelectTimeRange");
	Object obj = cls.newInstance();
	Class noparams[] = {};
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("may");
 
	System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			System.out.println("Staff id : " + eElement.getAttribute("id"));
			//System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
			//System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
			//System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
			//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
			System.out.println("Testname : " + eElement.getElementsByTagName("testname").item(0).getTextContent());
			String x = eElement.getElementsByTagName("testname").item(0).getTextContent();
			//Method method = getClass().getMethod(x);
			//method.invoke(this);
			System.out.println(x);
			Method method = cls.getDeclaredMethod(x, noparams);
			method.invoke(obj, null);
			
			
		}
	}
	/*Class nw = Class.forName("temp.SelectTimeRange");
	Object objc = nw.newInstance();
	NodeList fList = doc.getElementsByTagName("may");
	System.out.println("----------------------------");
	for (int temp = 0; temp < fList.getLength(); temp++) {
		 
		Node nNode = fList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			System.out.println("Staff id : " + eElement.getAttribute("id"));
			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
			System.out.println("Testname : " + eElement.getElementsByTagName("testname").item(0).getTextContent());
			String x = eElement.getElementsByTagName("testname").item(0).getTextContent();
			//Method method = getClass().getMethod(x);
			//method.invoke(this);
			System.out.println(x);
			Method method = nw.getDeclaredMethod(x, noparams);
			method.invoke(objc, null);
			
			
		}
	}*/
	
    } catch (Exception e) {
    	System.out.println("trace:");
	e.printStackTrace();
    }
  }
 
}