package org.plese.controller;


import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Retrieves login related pages
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * Retrieves the login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error,
											ModelMap model) {
		logger.debug("Received request to show login page");

		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true when authentication fails. 
		if (error == true) {
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		
		// This will resolve to /WEB-INF/pages/loginpage.jsp
		return "loginpage";
	}
	
	/**
	 * Retrieves the denied page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		
		// This will resolve to /WEB-INF/pages/deniedpage.jsp
		return "deniedpage";
	}

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogout(){
        logger.debug("Recieved request to logout.");

        SecurityContextHolder.clearContext();

        return "loginpage";
    }
}