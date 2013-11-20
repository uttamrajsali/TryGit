package org.nuance.testFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ReadXMLFile xmlRead= new ReadXMLFile();
        xmlRead.readXml("testcases.xml");
        GenerateHtml FinalRep = new GenerateHtml();
        FinalRep.execute();
    }
}
