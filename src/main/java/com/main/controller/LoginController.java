package com.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.model.UserDetails;
import com.main.service.UserService;

/**
 * @author Kandarp Dave
 * 
 * The Class LoginController
 */
@Controller
@RequestMapping("App/jsps")
public class LoginController {
	
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	/** User service. */
	@Autowired
	private UserService userService; 
	
	
	/**
	 * Map requests on index page
	 *
	 * @return the String to map index page
	 */
	@RequestMapping("/")
	public String byDefault() {
		
		logger.info("In index page... (Default method)");
		return "index";
	}
	
	/**
	 * For user authentication and retrieve appropriate data
	 *
	 * @param emailid for Email id or username of user
	 * @param password for password
	 * @param model the model
	 * @param Httpsession for store data in session variable
	 * @return the String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authentication(Model model, @RequestParam("emailId") String emailId, @RequestParam("password") String password,
			HttpSession session) {
		
		UserDetails ud = (UserDetails) userService.validUser(emailId, password);
		session.setAttribute("ud", ud);
		
		if(ud==null) {
			logger.info("---------------------------------------Invalid User..!---------------------------------------");
			return "redirect:/";
		}
		else {
			logger.info("---------------------------------------"+ud.getRole()+"---------------------------------------");
			if(ud.getRole().toString().equals("Admin")) {
				List<UserDetails> userData = userService.getAll();
				model.addAttribute("userData", userData);
				logger.info("---------------------------------------Admin Data---------------------------------------");
				return "adminData";
			}
			else {
				List<UserDetails> list = new ArrayList<UserDetails>();
				UserDetails userData = (UserDetails) userService.get(ud.getUserId());
				
				list.add(userData);
				model.addAttribute("userData", list);
				logger.info("---------------------------------------User Data---------------------------------------");
				return "adminData";
			}
		}
	}

	
}
