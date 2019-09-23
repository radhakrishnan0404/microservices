package com.svm.psms.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svm.psms.entities.User;
import com.svm.psms.services.LoginService;


@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	LoginService service;

	@RequestMapping(value = "/loginsession/{email}/{password}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String login(@PathVariable String email, @PathVariable String password) {

		User user = service.validateLogin(email, password);
		if (user == null)
			return "{\"status\":\"invalid\"}";

		if (user.getStatus().equalsIgnoreCase("active")) {
			System.out.println("_user.getType()___"+user.getType());
			if (user.getType().equalsIgnoreCase("user"))
				
				return "{\"status\":\"home\",\"user\":\"" + email + "\",\"type\":\"user\"}";
			else if (user.getType().equalsIgnoreCase("admin"))
				return "{\"status\":\"admin\",\"user\":\"" + email + "\",\"type\":\"admin\"}";
		}
		
		return "{\"status\":\"invalid\"}";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public String checkexistingUser(@RequestParam("loginid") String loginid, @RequestParam("password") String password) throws MessagingException {
		User user = service.validateLogin(loginid, password);
			byte[] bytesEncoded = Base64.getEncoder().encode(loginid.getBytes());
			String session=new String(bytesEncoded);
		if (user == null)
			return "{\"status\":\"invalid\"}";
		if (user.getStatus().equalsIgnoreCase("active")) {		
			if (user.getType().equalsIgnoreCase("user")){			
				return "{\"status\":\"home\",\"user\":\"" + session + "\",\"type\":\"user\"}";
			}else if (user.getType().equalsIgnoreCase("admin"))
				return "{\"status\":\"admin\",\"user\":\"" + session + "\",\"type\":\"admin\"}";
		}
		return "{\"status\":\"invalid\"}";
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/userdetail/{usertype}")
	private Map<String, Object> AddUser(@RequestBody User User,@PathVariable String usertype) {
		return this.service.AddUser(User,usertype);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteuserdetail")
	public String deleteUser(@RequestParam("loginid") String loginid) {
		return this.service.deleteUser(loginid);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/edituser")
	public User getUser(@RequestParam("loginid") String loginid) {
		
		return this.service.EditUser(loginid);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateuser/{usertype}")
	private Map<String, Object> updateUser(@RequestBody User User,@PathVariable String usertype) {
		return this.service.AddUser(User,usertype);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/userdetails")
	private List<User> GetAllUsers() {
		return this.service.getAllUserDetails();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/forgotpassword",headers="Accept=application/json")
	public String forgotPassword(@RequestParam("email_address") String email_address) throws MessagingException {
		return this.service.forgotPassword(email_address);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updatepassword", headers = "Accept=application/json")
	public String updatepassword(@RequestParam("randomnumber") String randomnumber,@RequestParam("password") String password)  {
		return this.service.updatepassword(randomnumber,password);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/changepassword")
	public String changePasssword(@RequestParam("loginid") String loginid,@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword) throws MessagingException {
		return this.service.changePasssword(loginid,oldpassword,newpassword);
	}
}
