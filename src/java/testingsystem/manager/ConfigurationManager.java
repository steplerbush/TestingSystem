/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.manager;

import java.util.ResourceBundle;

/**
 * Contains config strings and their in-program names. Implements Singleton
 * pattern.
 *
 * @author mirman
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;
    
    private static final String BUNDLE_NAME = "props/config";
    public static final String DAOFACTORY = "daofactory";
    public static final String CONTROLLER = "controller.path";
    public static final String INDEX_PAGE_PATH = "page.path.index";

    public static final String ERROR_PAGE_PATH = "page.path.error";
    public static final String LOGIN_PAGE_PATH = "page.path.login";

    public static final String REGISTER_PAGE_PATH = "page.path.register";
    public static final String REGISTER_DONE_PAGE_PATH
            = "page.path.registerdone";

    public static final String TUTOR_HOME_PATH = "page.path.tutor.home";
    public static final String STUDENT_HOME_PATH = "page.path.student.home";
    public static final String ADMIN_HOME_PATH = "page.path.admin.home";
    public static String NEWBIE_PATH="/TestingSystem/site";

    public static final String TUTOR_CREATE_TEST_PATH = "page.path.tutor.createtest";
    public static final String TUTOR_CONTINUE_CREATE_TEST_PATH = "page.path.tutor.continuecreatetest";

    private ResourceBundle resourceBundle;

    /**
     * Helper class, so private constructor.
     */
    private ConfigurationManager() {
    }

    /**
     * Returns ConfigurationManager instance.
     *
     * @return Ready-to-use ConfigurationManager instance.
     */
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    /**
     * Returns config string.
     *
     * @param key Key to search string for.
     * @return Returns config string.
     */
    public String getProperty(String key) {
        return this.resourceBundle.getString(key);
    }
}
