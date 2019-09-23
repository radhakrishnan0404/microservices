package com.svm.psms.dao;

import java.util.List;
import java.util.Map;

import com.svm.psms.entities.User;


public interface LoginDAO {

	public User validateLogin(String email,String password);

	public String forgotPassword(String email_address);

	public String updatepassword(String randomnumber, String password);
	
	public String changePasssword(String loginid, String oldpassword, String newpassword);

	public String deleteUser(String loginid);
	
	public Map<String, Object> AddUser(User user, String usertype);
	
	public List<User> getAllUserDetails();

	public User EditUser(String loginid);
	

}
