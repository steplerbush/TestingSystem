package testingsystem.manager;

/**
 * Contains attribute strings and their in-program names.
 *
 * @version 1.0 08.06.2011
 * @author Alex Nevsky
 */
public class AttributesManager {

    public static final String EMPTY_STRING = "";
    public static final String COMMAND_GO_LOGIN = "gologin";
    public static final String COMMAND_SUBMIT_LOGIN = "submitlogin";
    public static final String COMMAND_LOGOUT = "logout";
    public static final String COMMAND_LANG = "lang";
    public static final String COMMAND_GO_REGISTER = "goregister";
    public static final String COMMAND_SUBMIT_REGISTER = "submitregister";
    public static final String COMMAND_HOME = "home";
    public static final String COMMAND_CONTINUE_REGISTER = "continueregister";
    public static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    public static final String COMMAND_REMOVE_GROUP = "delstudentgroup";
    public static final String COMMAND_ADD_GROUP = "addstudentgroup";
    public static final String COMMAND_ADD_ADMIN = "addadmin";
    public static final String COMMAND_UNCHECKED_TUTOR_SHOW = "tutorshow";
    public static final String COMMAND_TUTOR_APPROVE = "tutorapprove";
    public static final String COMMAND_SHOW_SELECTED_GROUP = "showstudentgroup";
    public static final String COMMAND_ADMIN_CREAL_SESSION = "adminclearsession";

    public static final String ATTRIBUTE_YEAR = "year";
    public static final String ATTRIBUTE_RESULT = "result";
    public static final String ATTRIBUTE_LANG = "lang";

    public static final String ATTRIBUTE_LOCALE_RU = "ru";
    public static final String ATTRIBUTE_LOCALE_EN = "en";
    public static final String ATTRIBUTE_OK_MESSAGE = "okMessage";

    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_REG_USER = "reguser";
    public static final String ATTRIBUTE_REG_USER_ROLE = "reguserrole";
    public static final String ATTRIBUTE_USER_ROLE = "userRole";
    public static final String ADMIN_ROLE = "Admin";
    public static final String TUTOR_ROLE = "Tutor";
    public static final String STUDENT_ROLE = "Student";
    public static final String ATTRIBUTE_LOGIN = "login";
    public static final String ATTRIBUTE_LOGOUT = "logout";
    public static final String ATTRIBUTE_REGISTER = "register";
    public static final String ATTRIBUTE_GROUPS_LIST = "groupslist";
    public static final String ATTRIBUTE_UNCHECKED_TUTOR = "uncheckedtutor";
    public static final String ATTRIBUTE_UNCHECKED_TUTORS = "uncheckedtutors";
    public static final String ATTRIBUTE_SELECT_TUTOR = "seltutor";
    public static final String ATTRIBUTE_REMOVE_GROUP = "delstudentgroup";
    public static final String ATTRIBUTE_SELECTED_STUDENTGROUP = "selectedgroup";
    public static final String ATTRIBUTE_SELECTED_GROUP_STUDENTS = "studentsselgr";

    public static final String PARAM_NAME_ACTION = "action";
    public static final String PARAM_NAME_SPECIAL_USER_LOCALE = "specialUserLocale";

    public static final String PARAM_NAME_LOGIN = "login";
    public static final String PARAM_NAME_PASSWORD = "password";
    public static final String PARAM_NAME_FIRSTNAME = "firstname";
    public static final String PARAM_NAME_SECONDNAME = "secondname";
    public static final String PARAM_NAME_EMAIL = "email";
    public static final String PARAM_NAME_TELEPHONE = "telephone";
    public static final String PARAM_NAME_TUTOR_INFO = "info";
    public static final String PARAM_NAME_STUDENT_GROUP = "studentgroup";
    public static final String PARAM_NAME_REG_USER_ROLE = "roleradios";
    
    public static final String PARAM_ADM_LOGIN = "admlogin";
    public static final String PARAM_ADM_PASS = "admpassword";
    public static final String PARAM_ADM_FIRSTNAME = "admfirstname";
    public static final String PARAM_ADM_SECONDNAME = "admsecondname";
    public static final String PARAM_ADM_EMAIL = "admemail";

    public static final String FILTER_PARAM_ADMIN_AREA = "AdminArea";
    public static final String FILTER_PARAM_TUTOR_AREA = "TutorArea";
    public static final String FILTER_PARAM_STUDENT_AREA = "StudentArea";
    public static final String FILTER_PARAM_AUTHORIZED_AREA = "AuthArea";

    public static final String DAO_MESSAGE_LOGIN_EXISTS = "loginexists";
    public static final String DAO_MESSAGE_EMAIL_EXISTS = "emailexists";

    public static final String PARAM_NAME_COMMENTARY = "commentary";

    public static final String PARAM_NAME_LOCALE = "locale";
    public static final String PARAM_GROUP_NAME = "groupname";
    public static final String PARAM_GROUP_NUMBER = "groupnumber";

    /**
     * Helper class, so private constructor.
     */
    private AttributesManager() {

    }
}
