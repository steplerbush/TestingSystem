/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.manager;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author mirman
 */
public class MessageManager {

    private static final String BUNDLE_NAME = "props/messages";

    public static final String SYSTEM_TITLE = "system.title";
    public static final String WELCOME_TITLE = "welcome.title";
    public static final String ERROR_TITLE = "error.title";

    public static final String COMMAND_RESERVE_ROOM = "command.reserve.room";

    public static final String LOGIN_ERROR_MESSAGE = "exception.login.error.message";
    public static final String REGISTER_EMPTY_ERROR = "exception.register.empty.error.message";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "exception.servlet.error.message";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "exception.io.error.message";
    public static final String DAO_EXCEPTION_ERROR_MESSAGE = "exception.dao.error.message";
    public static final String PERMISSION_EXCEPTION_ERROR_MESSAGE = "exception.permission.error.message";
    public static final String WRONG_DATE_ERROR_MESSAGE = "exception.wrong.date.error.message";
    public static final String ALREADY_LOGGEDIN_ERROR_PATH = "exception.register.logged.error.message";
    public static final String REGISTER_LOGIN_EXISTS_ERROR = "exception.register.login.exists.error.message";
    public static final String REGISTER_EMAIL_EXISTS_ERROR = "exception.register.email.exists.error.message";
    public static final String AUTH_ACCESS_DENIED_MESSAGE = "exception.auth.access.denied.error.message";
    public static final String NON_AUTH_ACCESS_DENIED_MESSAGE = "exception.nonauth.access.denied.error.message";
    public static final String GO_HOME_ERROR_MESSAGE = "exception.gohome.error.message";
    public static final String DEFAULT_GROUP_REMOVING_ERROR_MESSAGE = "exception.remove.def.group.error.message";

    public static final String CREATED_STUDENTGROUP_MESSAGE = "ok.created.studentgroup.message";
    public static final String CREATE_EMPTY_ADMIN_MESSAGE = "exception.empty.admincreation.message";

    public static final String TAG_ID = "tag.id";
    public static final String TAG_FIRST_NAME = "tag.first.name";
    public static final String TAG_LAST_NAME = "tag.last.name";
    public static final String TAG_EMAIL = "tag.email";
    public static final String TAG_PHONE = "tag.phone";
    public static final String TAG_ADDRESS = "tag.address";

    public static final String CREATE_EXIST_STUDENTGROUP_MESSAGE = "exception.create.group.exists.error.message";
    public static final String OPERATION_ERROR_MESSAGE = "exception.operation.error.message";
    public static final String CREATED_ADMIN_MESSAGE = "ok.created.admin.message";
    public static final String REMOVED_STUDENTGROUP_MESSAGE = "ok.removed.group.message";
    public static final String EMPTYTESTTITLE_MESSAGE = "empty.title.error.message";
    public static final String EMPTYTESTDESCRIPTION_MESSAGE = "empty.description.error.message";
    public static final String NOT_ZERO_DURATION_MESSAGE = "not.zero.duraion.error.message";
    public static final String TIME_ERROR_MESSAGE = "time.error.message";

    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    private HashMap<Locale, ResourceBundle> localeResourceBundle = new HashMap<Locale, ResourceBundle>();

    /**
     * Helper class, so private constructor.
     */
    private MessageManager() {
    }

    /**
     * Returns MessageManager instance.
     *
     * @return Ready-to-use MessageManager instance.
     */
    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    /**
     * Returns string for default locale. Default locale is server locale.
     *
     * @param key Key to search string for.
     * @return Returns string for default locale.
     */
    public String getProperty(String key) {
        return this.resourceBundle.getString(key);
    }

    /**
     * Returns string for specified locale.
     *
     * @param key Key to search string for.
     * @param loc Preferred locale.
     * @return Returns string for specified locale.
     */
    public String getProperty(String key, Locale loc) {
        ResourceBundle res = null;
        if (this.localeResourceBundle.containsKey(loc)) {
            res = this.localeResourceBundle.get(loc);
        } else {
            res = ResourceBundle.getBundle(BUNDLE_NAME, loc);
            this.localeResourceBundle.put(loc, res);
        }
        return res.getString(key);
    }
}
