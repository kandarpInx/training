package com.main.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.main.service.AddressService;
import com.main.service.UserService;

/**
 * @author Kandarp Dave
 * 
 * The Class UserController
 */
@Controller
@RequestMapping("App/jsps")
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	
	/** User service. */
	@Autowired
	private UserService userService;
	
	/** Address service. */
	@Autowired
	private AddressService addressService;

	
	/**
	 * List the addresses of particular user on modal popup
	 *
	 * @param id is User id
	 * @param model the model
	 * @return the List
	 */
	@RequestMapping(value = "/ListAddress", method = RequestMethod.POST)
	public @ResponseBody List<Address> AllAddressOfUser(Model model, @RequestParam("userId") Long userId,
			HttpServletRequest request, HttpServletResponse response) {
		List<Address> addr = userService.get(userId).getAddresses();
		return addr;
	} 
	
	/**
	 * To display the blank form at time of new registration
	 *
	 * @param model the model
	 * @return the String to redirect at registration page
	 */
	@RequestMapping(value="/showDetails",method=RequestMethod.GET)
    public String insertDetails(Model model) {
		List<Address> address = new ArrayList<Address>();
		address.add(new Address());
		model.addAttribute("addressData", address);
		return "register";
    }
	
	
	/**
	 * Insert and Update User details along with multiple addresses.
	 *
	 * @param userdetails is user details
	 * @param bindingresult is for checking bean validation and error messages
	 * @param addrId is AddressId 
	 * @param street1 is Street 1
	 * @param street2 is Street 2
	 * @param pincode is Pincode
	 * @param city is Name of city
	 * @param state is name of state
	 * @param country is name of country
	 * @return the string to redirect page
	 */
	@RequestMapping(value="/saveUser", method = RequestMethod.POST)
	public String saveUserDetails(@Valid  @ModelAttribute UserDetails userDetails, BindingResult result,
			@RequestParam("addrId") List<Long> addrId,
			@RequestParam("street1") List<String> street1,
			@RequestParam("street2") List<String> street2,
			@RequestParam("pincode") List<Integer> pincode,
			@RequestParam("city") List<String> city,
			@RequestParam("state") List<String> state,
			@RequestParam("country") List<String> country) {
		
		logger.info(userDetails.getUserId());
		logger.info(userDetails.getEmailId());
		logger.info(userDetails.getRole());
		
		/*List<Address> newAddress = new ArrayList<Address>();
		Set<Long> newAddressId = new HashSet<Long>();
		
		for (int i=0; i<street1.size(); i++) {
			Address addr = new Address();
			addr.setAddrId(addrId.get(i));
			addr.setStreet1(street1.get(i));
			addr.setStreet2(street2.get(i));
			addr.setPincode(pincode.get(i));
			addr.setCity(city.get(i));
			addr.setCountry(country.get(i));
			addr.setState(state.get(i));
			addr.setUserDetails(userDetails);
			newAddress.add(addr);
			newAddressId.add(newAddress.get(i).getAddrId());
		}*/
		
		
		
		List<Address> afterAddressData = new ArrayList<Address>();
		
		for (int i=0; i<street1.size(); i++) {
			Address addr = new Address();
			addr.setAddrId(addrId.get(i));
			addr.setStreet1(street1.get(i));
			addr.setStreet2(street2.get(i));
			addr.setPincode(pincode.get(i));
			addr.setCity(city.get(i));
			addr.setCountry(country.get(i));
			addr.setState(state.get(i));
			addr.setUserDetails(userDetails);
			afterAddressData.add(addr);
		}
		
		
		if (result.hasErrors()) {
			logger.info("===============================Errors====================================");
			logger.info(result.getAllErrors());
			logger.info("=========================================================================");
			return "register";
		}
 
		if (userDetails.getUserId() == null || userDetails.getUserId() == 0) {
			System.out.println("=========== New User ===========");
			userDetails.setAddresses(afterAddressData);
			userService.save(userDetails);
		} else {
			System.out.println("=========== Existing user ===========");
			
			List<Address> beforeAddressData = userService.get(userDetails.getUserId()).getAddresses();

			for (int i = 0; i < beforeAddressData.size(); i++) {
				logger.info("Before address======Street 1====="+beforeAddressData.get(i).getStreet1());
			}
			Set<Long> insertedIds = new HashSet<Long>();
			Set<Long> deletedIds = new HashSet<Long>();
			List<Address> newlyInsertedModels = new ArrayList<Address>();
			List<Address> updatedModels = new ArrayList<Address>();
			
			for (int i = 0; i < afterAddressData.size(); i++) {
				logger.info("After address======Street 1====="+afterAddressData.get(i).getAddrId());
				logger.info("After address======Street 1====="+afterAddressData.get(i).getStreet1());
			}
			
			for(Address afterAddressModel : afterAddressData) {
				
				boolean isUpdateAddress = false;
				
				for(Address beforeAddressModel : beforeAddressData) {
					if(beforeAddressModel.getAddrId().equals(afterAddressModel.getAddrId())) 
					{
						
						if(!beforeAddressModel.getStreet1().equals(afterAddressModel.getStreet1()) ||
								!beforeAddressModel.getStreet2().equals(afterAddressModel.getStreet2()) ||
								!beforeAddressModel.getPincode().equals(afterAddressModel.getPincode()) ||
								!beforeAddressModel.getCity().equals(afterAddressModel.getCity()) ||
								!beforeAddressModel.getState().equals(afterAddressModel.getState()) ||
								!beforeAddressModel.getCountry().equals(afterAddressModel.getCountry())) 
						{	
							updatedModels.add(afterAddressModel);
							isUpdateAddress = true;
						}
						insertedIds.add(afterAddressModel.getAddrId());
					}
				}
				if(!isUpdateAddress && afterAddressModel.getAddrId()==null) 
				{
					newlyInsertedModels.add(afterAddressModel);
				}
			}
			
			for(Address beforeAddressModel : beforeAddressData) {
				if(!insertedIds.contains(beforeAddressModel.getAddrId())) 
				{
					deletedIds.add(beforeAddressModel.getAddrId());
				}
			}
			
			for (Address model : newlyInsertedModels) {
				logger.info("Insert Street1:" + model.getStreet1());
			}

			for (Address am : updatedModels) {
				addressService.update(am);
				logger.info("Update query on :: " + am.getAddrId());
			}
			for (Long id : deletedIds) {
				Address addr = addressService.get(id);
				addressService.delete(addr);
				logger.info("Delete query on :: " + id);
			}
			userDetails.setAddresses(newlyInsertedModels);
			userService.update(userDetails);
		}
		return "adminData";
		
		
		
		
		/*
		 * List oldAddress = userService.getAddressId(id);
		 * 
		 * for(int i=0; i < oldAddress.size(); i++) {
		 * if(!(newAddressId.contains(oldAddress.get(i)))) { int oldid = (int)
		 * oldAddress.get(i); userService.removeAddress(oldid); } }
		 * 
		 * 
		 * } user.setAddress(newAddress); userService.update(user);
		 */

	}
	
	/**
	 * Display filled form at the time of updating details.
	 *
	 * @param id is User id
	 * @param model the model
	 * @return the string
	 */
	
	@RequestMapping(value="/updateForm", method=RequestMethod.POST)
    public String updateItem(@RequestParam("userId") Long id, Model model) {
		UserDetails userDetails = (UserDetails) userService.get(id);
		List<Address> address = userService.get(id).getAddresses();
        model.addAttribute("data", userDetails);
        model.addAttribute("addressData", address);
        return "register";
    }
	
}
