/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.tutor;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import testingsystem.commands.ICommand;
import testingsystem.controllers.FrontController;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;
import testingsystem.model.beans.Test;
import testingsystem.model.beans.TestLocale;
import testingsystem.model.beans.Tutor;

/**
 *
 * @author mirman
 */
public class ContinueTestCreationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        if (checkForProblems(request)) {
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.TUTOR_CREATE_TEST_PATH);
        }
        Test test = new Test();
        saveTestToDB(request, test);
        saveLocaleToDB(request, test);
        clearSession(request);
        request.getSession().setAttribute(
                AttributesManager.ATTRIBUTE_TEMPTEST, test);
        return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.TUTOR_CONTINUE_CREATE_TEST_PATH);
    }
////////////////////////////////
    private void saveTestToDB(HttpServletRequest request, Test test) {
        try {
            getTimesOfTest(request, test);
        } catch (ParseException ex) {
            Logger.getLogger(ContinueTestCreationCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        test.setTutorId(((Tutor) request.getSession().getAttribute(
                AttributesManager.ATTRIBUTE_USER)).getId());
        
    }

    private void saveLocaleToDB(HttpServletRequest request, Test test)
            throws NumberFormatException {
        TestLocale locale = new TestLocale();
        locale.setLang(request.getParameter(
                AttributesManager.PARAM_SELECT_LANG));
        locale.setTitle(request.getParameter(
                AttributesManager.ATTRIBUTE_TEST_TITLE));
        locale.setDescription(request.getParameter(
                AttributesManager.ATTRIBUTE_TEST_DESCRIPTION));
        test.setLocale(locale);
        
    }

    private void getTimesOfTest(HttpServletRequest request, Test test)
            throws ParseException {
        SimpleDateFormat parserDT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String openDT = request.getParameter(
                AttributesManager.ATTRIBUTE_OPENING_TIME);
        test.setOpenTime(new Timestamp(parserDT.parse(openDT).getTime()));
        String closeDT = request.getParameter(
                AttributesManager.ATTRIBUTE_CLOSING_TIME);
        test.setCloseTime(new Timestamp(parserDT.parse(closeDT).getTime()));
        SimpleDateFormat parserT = new SimpleDateFormat("HH:mm:ss");
        String durationT = request.getParameter(
                AttributesManager.ATTRIBUTE_DURATION);
        test.setDuration(new Time(parserT.parse(durationT).getTime()));
    }

    private boolean checkForProblems(HttpServletRequest request) {
        if (request.getParameter(AttributesManager.ATTRIBUTE_TEST_TITLE)
                .equals("")) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.EMPTYTESTTITLE_MESSAGE);
            return true;
        }
        if (request.getParameter(AttributesManager.ATTRIBUTE_TEST_DESCRIPTION)
                .equals("")) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.EMPTYTESTDESCRIPTION_MESSAGE);
            return true;
        }
        if (request.getParameter(AttributesManager.ATTRIBUTE_DURATION)
                .equals(AttributesManager.DURATION_NULL)) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.NOT_ZERO_DURATION_MESSAGE);
            return true;
        }
        if (request.getParameter(AttributesManager.ATTRIBUTE_CLOSING_TIME)
                .equals(request.getParameter(
                                AttributesManager.ATTRIBUTE_OPENING_TIME))) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.TIME_ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE);
        session.removeAttribute(AttributesManager.ATTRIBUTE_DURATION);
        session.removeAttribute(AttributesManager.ATTRIBUTE_OPENING_TIME);
        session.removeAttribute(AttributesManager.ATTRIBUTE_CLOSING_TIME);
        session.removeAttribute(AttributesManager.ATTRIBUTE_LANG_LIST);
    }

}
