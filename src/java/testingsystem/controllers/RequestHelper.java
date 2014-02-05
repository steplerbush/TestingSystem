/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.controllers;

import testingsystem.manager.AttributesManager;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import testingsystem.commands.ICommand;
import testingsystem.commands.admin.AdminClearSessionCommand;
import testingsystem.commands.admin.AdminRegisterCommand;
import testingsystem.commands.admin.CreateStudentGroupCommand;
import testingsystem.commands.admin.RemoveStudentGroupCommand;
import testingsystem.commands.admin.ShowSelectedGroupCommand;
import testingsystem.commands.admin.TutorApproveCommand;
import testingsystem.commands.admin.UnckeckedTutorShowCommand;
import testingsystem.commands.general.*;

/**
 *
 * @author mirman
 */
/**
 * Request helper class for resolving actions. Implements Singleton pattern.
 */
public class RequestHelper {

    static {
        logger = Logger.getLogger(RequestHelper.class);
    }
    private static final Logger logger;
    private static RequestHelper instance = null;
    /**
     * All available commands catalog.
     */
    private final HashMap<String, ICommand> commandCatalog = new HashMap<>();

    /**
     * Construct command's catalogues.
     */
    private RequestHelper() {
        // general commands
        this.commandCatalog.put(AttributesManager.COMMAND_GO_LOGIN,
                new GoLoginCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_SUBMIT_LOGIN,
                new LoginCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_LOGOUT,
                new LogoutCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_LANG,
                new LangCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_GO_REGISTER,
                new GoRegisterCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_CONTINUE_REGISTER,
                new ContinueRegisterCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_SUBMIT_REGISTER,
                new SubmitRegisterCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_HOME,
                new HomeCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_UNCHECKED_TUTOR_SHOW,
                new UnckeckedTutorShowCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_TUTOR_APPROVE,
                new TutorApproveCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_ADD_GROUP,
                new CreateStudentGroupCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_SHOW_SELECTED_GROUP,
                new ShowSelectedGroupCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_REMOVE_GROUP,
                new RemoveStudentGroupCommand());

        //Admin commands
        this.commandCatalog.put(AttributesManager.COMMAND_UNCHECKED_TUTOR_SHOW,
                new UnckeckedTutorShowCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_TUTOR_APPROVE,
                new TutorApproveCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_ADD_GROUP,
                new CreateStudentGroupCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_SHOW_SELECTED_GROUP,
                new ShowSelectedGroupCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_REMOVE_GROUP,
                new RemoveStudentGroupCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_ADMIN_CREAL_SESSION,
                new AdminClearSessionCommand());
        this.commandCatalog.put(AttributesManager.COMMAND_ADD_ADMIN,
                new AdminRegisterCommand());

        // customer commands
//        this.commandCatalog.put(AttributesManager.COMMAND_BOOKING_ROOM, new BookingRoomCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_PROCESS_FORM, new ProcessFormCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_SHOW_MY_ORDERS, new ShowMyOrdersCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_SHOW_MY_BILL, new ShowMyBillCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_CANCEL_MY_ORDER, new CancelMyOrderCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_DELETE_MY_ORDER, new DeleteMyOrderCommand());
//
//        // admin commands
//        this.commandCatalog.put(AttributesManager.COMMAND_VIEW_ROOMS, new ViewRoomsCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_VIEW_ORDERS, new ViewOrdersCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_VIEW_BILL, new ViewBillCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_VIEW_CUSTOMERS, new ViewCustomersCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_CANCEL_ORDER, new CancelOrderCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_DELETE_ORDER, new DeleteOrderCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_FIND_ROOM, new FindRoomCommand());
//        this.commandCatalog.put(AttributesManager.COMMAND_SELECT_ROOM, new SelectRoomCommand());
    }

    /**
     * Returns RequestHelper instance.
     *
     * @return Ready-to-use RequestHelper instance.
     */
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }

    /**
     * Parses given request and finds "command" parameter in it.
     *
     * @param request User's request to parse.
     * @return Corresponding for this purposes ICommand implementor.
     */
    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter(AttributesManager.PARAM_NAME_ACTION);

        logger.info("User '" + request.getSession().
                getAttribute(AttributesManager.PARAM_NAME_LOGIN)
                + "'. " + "Request command action: '" + action + "'"
                + ". RemoteAddr: " + request.getRemoteAddr());

        ICommand command = this.processRequest(request, action);

        if (command == null) {
            command = new NoCommand();
        }

        return command;
    }

    /**
     * Finds corresponding ICommand implementor and returns it. Also checks the
     * user's permissions and authorization.
     *
     * @param request User's request.
     * @param action User's action.
     * @return Corresponding for this purposes ICommand implementor.
     */
    private ICommand processRequest(HttpServletRequest request, String action) {
        ICommand command = null;
        if (action == null) {
            command = new NoCommand();
        } else {
            command = this.commandCatalog.get(action);
        }
        return command;
    }

    private ICommand notAuthUserActionCheck(String action, ICommand command,
            HttpServletRequest request) {
        //lang command
        if (action.equalsIgnoreCase(
                AttributesManager.ATTRIBUTE_LANG)) {
            command = new LangCommand();
            logger.info("Not auth user change language: '"
                    + command + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
            //continue register command
        } else if (action.equalsIgnoreCase(
                AttributesManager.COMMAND_CONTINUE_REGISTER)) {
            logger.info("Not auth user continues register: '"
                    + command + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
            //go register command
        } else if (action.equalsIgnoreCase(
                AttributesManager.COMMAND_GO_REGISTER)) {
            logger.info("Not auth user begins register: '"
                    + command + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
            //submit register command
        } else if (action.equalsIgnoreCase(
                AttributesManager.COMMAND_SUBMIT_REGISTER)) {
            logger.info("Not auth user submits register: '"
                    + command + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
            //submit login command
        } else if (action.equalsIgnoreCase(
                AttributesManager.COMMAND_SUBMIT_LOGIN)) {
            logger.info("Not auth user tries to log in: '"
                    + command + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
        } else { // not lang command. login command: default command for not auth user
            command = new GoLoginCommand();
            logger.warn("Action '" + action + "' from not auth user. "
                    + "Create and send command to controller: '"
                    + command + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
        } // end if lang action
        return command;
    }

    /**
     * Returns true if an authorized user sends request.
     *
     * @param request User's request.
     * @return True if an authorized user sends request.
     */
    public static boolean isAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return false;
        }
        return session.getAttribute(
                AttributesManager.ATTRIBUTE_USER_ROLE) != null;
    }

    public static boolean isAdminUser(HttpServletRequest request) {
        return isRoleUser(request, AttributesManager.ADMIN_ROLE);
    }

    public static boolean isTutorUser(HttpServletRequest request) {
        return isRoleUser(request, AttributesManager.TUTOR_ROLE);
    }

    public static boolean isStudentUser(HttpServletRequest request) {
        return isRoleUser(request, AttributesManager.STUDENT_ROLE);
    }

    private static boolean isRoleUser(HttpServletRequest request,
            String userRole) {
        HttpSession session = request.getSession();
        if (session == null) {
            return false;
        }
        return ((String) session.getAttribute(
                AttributesManager.ATTRIBUTE_USER_ROLE)).equals(userRole);
    }
}
