package com.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.model.Address;
import com.main.model.UserDetails;
import com.main.service.UserService;

@Controller
@RequestMapping("App/jsps")
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/ListAddress", method = RequestMethod.POST)
	public @ResponseBody List<Address> AllAddressOfUser(Model model, @RequestParam("userId") Long userId,
			HttpServletRequest request, HttpServletResponse response) {
		List<Address> addr = userService.get(userId).getAddresses();
		return addr;
	} 
	
	@RequestMapping(value="/showDetails",method=RequestMethod.GET)
    public String insertDetails(Model model) {
		List<Address> address = new ArrayList<Address>();
		address.add(new Address());
		model.addAttribute("addressData", address);
		return "register";
    }
	
	@RequestMapping(value="/saveUser", method = RequestMethod.POST)
	public String saveUserDetails(@Valid  @ModelAttribute UserDetails userDetails, BindingResult result, 
			@RequestParam("street1") List<String> street1,
			@RequestParam("street2") List<String> street2,
			@RequestParam("pincode") List<Integer> pincode,
			@RequestParam("city") List<String> city,
			@RequestParam("state") List<String> state,
			@RequestParam("country") List<String> country) {
		
		logger.info(userDetails.getUserId());
		logger.info(userDetails.getEmailId());
		logger.info(userDetails.getRole());
		
		List<Address> addresses = new ArrayList<Address>();
		
		
		for (int index = 0; index < street1.size(); index++) {
			Address addr = new Address();
			addr.setStreet1(street1.get(index));
			addr.setStreet2(street2.get(index));
			addr.setPincode(pincode.get(index));
			logger.info(pincode.get(index));
			addr.setCity(city.get(index));
			addr.setCountry(country.get(index));
			addr.setState(state.get(index));
			addr.setUserDetails(userDetails);
			addresses.add(addr);
		}
		
		
		if (result.hasErrors()) {
			logger.info("===============================Errors====================================");
			logger.info(result.getAllErrors());
			logger.info("=========================================================================");
			return "register";
		}
 
		if (userDetails.getUserId() == null || userDetails.getUserId() == 0) {
			System.out.println("=========== New User ===========");
			userDetails.setAddresses(addresses);
			userService.save(userDetails);
		} else {
			System.out.println("=========== Existing user ===========");
			userService.update(userDetails);
		}
		return "redirect:/";

	}
	
	
	
	@RequestMapping(value="/updateForm", method=RequestMethod.POST)
    public String updateItem(@RequestParam("userId") Long id, Model model) {
		UserDetails userDetails = (UserDetails) userService.get(id);
		List<Address> address = userService.get(id).getAddresses();
        model.addAttribute("data", userDetails);
        model.addAttribute("addressData", address);
        return "register";
    }

}
