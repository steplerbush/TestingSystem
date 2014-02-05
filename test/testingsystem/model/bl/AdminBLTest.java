/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testingsystem.model.bl;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testingsystem.controllers.FrontController;
import testingsystem.dao.DAOFactory;
import testingsystem.manager.ConfigurationManager;
import testingsystem.model.beans.Tutor;

/**
 *
 * @author mirman
 */
public class AdminBLTest {
    
    public AdminBLTest() {
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
     * Test of getUnCkeckedTutors method, of class AdminBL.
     */
    @Test
    public void testGetUnCkeckedTutors() {
        System.out.println("getUnCkeckedTutors");
        AdminBL instance = new AdminBL(DAOFactory.getDAOFactory(ConfigurationManager.
                    getInstance().getProperty(ConfigurationManager.DAOFACTORY)));
        List<Tutor> expResult = null;
        List<Tutor> result = instance.getUnCkeckedTutors(); 
        for (Tutor t : result) {
            System.out.println(t.getUser().getFirstName() + " " + t.getUser().getSecondName() + " " + t.getUser().getLogin()+ " " +t.getTelephone());
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
