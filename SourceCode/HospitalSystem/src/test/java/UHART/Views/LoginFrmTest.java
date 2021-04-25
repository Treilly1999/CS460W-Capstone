/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Views;

import javax.swing.JPanel;
import junit.framework.TestCase;

/**
 *
 * @author A
 */
public class LoginFrmTest extends TestCase {
    
    public LoginFrmTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getLoginForm method, of class LoginFrm.
     */
    public void testGetLoginForm() {
        System.out.println("getLoginForm");
        LoginFrm instance = null;
        JPanel expResult = null;
        JPanel result = instance.getLoginForm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
