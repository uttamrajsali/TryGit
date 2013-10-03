package org.yujie.oditest.cssr;

import org.yujie.oditest.cssr.*;
import org.openqa.selenium.internal.seleniumemulation.IsAlertPresent;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */

public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        Login logintest = new Login();
        logintest.execute();
        Timestep selecttest = new Timestep();
        SelectTimeRange selectcssrtest = new SelectTimeRange();
        //Test cases begins
        /*selectcssrtest.findthelables();
        selectcssrtest.search();
        selectcssrtest.dnisfilter();
        selectcssrtest.abfilter();
        selectcssrtest.cssrNumbers();
        selectcssrtest.numberFormat();
        selecttest.Search();
        selecttest.EnhancementFromPortal();
        selecttest.DNISFilterNewLook();
        selecttest.hourSelection();
        selecttest.daySelection();
        selecttest.weekSelection();
        selecttest.monthSelection();*/
        //selecttest.daySelectionSorting();
        selecttest.weekSelectionSorting();
       // selecttest.monthSelectionSorting();
        selecttest.exitdriver();
        //Exporting the results to HTML
        GenerateHtml FinalRep = new GenerateHtml();
        FinalRep.execute();
    }
    

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
