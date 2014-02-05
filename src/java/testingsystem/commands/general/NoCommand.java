package testingsystem.commands.general;

import testingsystem.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import testingsystem.manager.ConfigurationManager;

/**
 * This default command with no actions.
 * 
 * @version 1.0 11.05.2011
 * @author Alex Nevsky
 */
public class NoCommand implements ICommand {

	/**
	 * Just redirects user to the main page.
	 */
	@Override
	public String execute(HttpServletRequest request, 
                HttpServletResponse response) {
		String page = ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.INDEX_PAGE_PATH);
		return page;
	}

	@Override
	public String toString() {
		return "NoCommand{}";
	}
}
