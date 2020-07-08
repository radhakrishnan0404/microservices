package com.svm.psms.services;

import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.svm.psms.dao.LoginDAO;
import com.svm.psms.entities.User;


@Service
public class LoginService {

	@Autowired
	LoginDAO repository;

	public User validateLogin(String email,String password) {
		return repository.validateLogin(email,password);
	}

	public String forgotPassword(String email_address) throws MessagingException {

		return repository.forgotPassword(email_address);
	}
	
	public String updatepassword(String randomnumber, String password) {

		return repository.updatepassword(randomnumber, password);
	}

	public String changePasssword(String loginid, String oldpassword, String newpassword) {

		return repository.changePasssword(loginid, oldpassword, newpassword);
	}
	
	public Map<String, Object> AddUser(User user, String usertype) {
		return repository.AddUser(user,usertype);
	}
	
	public User EditUser(String loginid) {
		return repository.EditUser(loginid);
	}
	
	public List getAllUserDetails() {
		return repository.getAllUserDetails();
	}

	public String deleteUser(String loginid) {

		return repository.deleteUser(loginid);
	}
	
	
}
