package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import java.util.*;

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
    public void testApp() {
        assertTrue( true );
    }
    public void testFound() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(50,40,80,90));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(50,40,87,95));
        double result = 66.8;
        assertEquals(result, new App().calculate(array, 40, array2, 60));
    }

    public void testEmptyArray() {
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        double result = -1;
        assertEquals(result,new App().calculate(array, 40,array2,60));

    }

    public void testZeroArgument() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(50,40,80,90));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(50,40,87,95));
        double result = -2;
        assertEquals(result,new App().calculate(array, 0,array2,60));

    }

    public void testNull() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(50,40,80,90));
        double result = -3;
        assertEquals(result,new App().calculate(array, 0,null,60));
    }

    public void testRatioSum() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(50,40,80,90));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(50,40,87,95));
        double result = -4;
        assertEquals(result,new App().calculate(array, 60,array2,60));
    }
  
}
