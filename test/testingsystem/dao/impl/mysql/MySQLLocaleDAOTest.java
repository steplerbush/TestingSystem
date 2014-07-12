/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testingsystem.dao.impl.mysql;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirmanstep
 */
public class MySQLLocaleDAOTest {
    
    public MySQLLocaleDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllLocales method, of class MySQLLocaleDAO.
     */
    @Test
    public void testGetAllLocales() {
        System.out.println("getAllLocales");
        MySQLLocaleDAO instance = new MySQLLocaleDAO();
        Map<Integer, String> expResult = null;
        Map<Integer, String> result = instance.getAllLocales();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class MySQLLocaleDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String locale = "";
        MySQLLocaleDAO instance = new MySQLLocaleDAO();
        int expResult = 0;
        int result = instance.insert(locale);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
