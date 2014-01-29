/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testingsystem.commands.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import testingsystem.commands.ICommand;
import testingsystem.manager.ConfigurationManager;

/**
 *
 * @author mirman
 */
public class TestCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.STUDENT_HOME_PATH);
    }
    
}
