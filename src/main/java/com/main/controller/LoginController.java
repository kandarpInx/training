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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.main.model.UserDetails;
import com.main.service.UserService;

/**
 * @author Kandarp Dave
 * 
 * The Class LoginController which is used to perform tasks like authentication, logging out, 
 * display data and forgot password
 */
@Controller
@SessionAttributes("userDetails")
@RequestMapping("App/ftls")
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
	public String authentication(Model model, @RequestParam("emailId") String emailId, @RequestParam("password") String password) {
		
		UserDetails ud = (UserDetails) userService.validUser(emailId, password);
		
		if(ud==null) {
			model.addAttribute("error", "Invalid username or password");
			return "index";
		}
		else {
			model.addAttribute("userDetails",ud);
			return "redirect:displayData";
		}
	}
	
	/**
	 * This method is to get appropriate data as per user role
	 * 
	 * @param session is to check if session is running or not
	 * @param model is to set data on model
	 * @return String as name of page where our request goes
	 */
	@RequestMapping(value = "/displayData", method = RequestMethod.GET)
	public String displayData(HttpSession session, Model model) {
		
		UserDetails ud = (UserDetails) session.getAttribute("userDetails");
		
		if(ud!=null)  {
			if(ud.getRole().toString().equals("Admin")) {
				List<UserDetails> userData = userService.getAll();
				model.addAttribute("userData", userData);
				return "adminData";
			}
			else {
				List<UserDetails> list = new ArrayList<UserDetails>();
				UserDetails userData = (UserDetails) userService.get(ud.getUserId());
				list.add(userData);
				model.addAttribute("userData", list);
				return "adminData";
			}
		}
		else {
			model.addAttribute("error", "Please login first");
			return "index";
		}
	}
	
	/**
	 * This method is for logout from their session
	 * 
	 * @param status is for sessionstatus whether it is running or off
	 * @param model is to set appropriate message on screen
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutSession(SessionStatus status, Model model) {
		status.setComplete();
		model.addAttribute("error", "Logged out successfully");
		return "index";
	}
	
	/**
	 * This method is for display Forgot password form
	 * 
	 * @param model is to set empty form
	 * @return String as forgot page name
	 */
	@RequestMapping(value="/forgotPassword",method=RequestMethod.GET)
    public String forgotForm(Model model) {
		return "forgot";
    }
	
	/**
	 * This method is used to get password at a time of forgotpassword
	 * 
	 * @param emailId is for Email id of user
	 * @param model is for setting message on screen
	 * @return
	 */
	@RequestMapping(value = "/forgotData", method = RequestMethod.POST)
	public String forgotData(@RequestParam("emailId") String emailId, Model model)
	{
		try {
			String password = userService.forgotPassword(emailId);
			model.addAttribute("error1", "Your password is "+password);
		}
		catch(IndexOutOfBoundsException e){
			model.addAttribute("error1", "Sorry!! Incorrect email address...please insert valid email address");
		}
		return "forgot";
	}
	
}
