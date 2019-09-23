package com.svm.psms.implementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.jsoup.nodes.Document;

import com.svm.psms.common.CryptoLibrary;
import com.svm.psms.config.RandomNumber;
import com.svm.psms.dao.LoginDAO;
import com.svm.psms.entities.ContactUsMails;
import com.svm.psms.entities.EmailConfig;
import com.svm.psms.entities.EmailTemplate;
import com.svm.psms.entities.LoginDetail;
import com.svm.psms.entities.User;

import java.text.DateFormat;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory _session;

	@Override
	@Transactional
	public User validateLogin(String email, String password) {
		// System.out.println("______"+email);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d = dateFormat.format(date);

		User uk = this._session.getCurrentSession().get(User.class, email);

		if (uk == null) {
			return null;
		} else {
			if (uk.getPassword().equalsIgnoreCase(MD5(password))) {
				System.out.println("___uk.getPassword()______" + uk + "__" + uk.getPassword() + "___" + MD5(password));
				String key = MD5(uk.getUsername() + d);
				if (key != null) {
					System.out.println("___LOGIN____" + uk.getUsername());
					LoginDetail logindetail = new LoginDetail();
					logindetail.setSession_id(key);
					logindetail.setLoginID(uk.getUsername());
					logindetail.setLog_in(new Date());
					logindetail.setStatus("Login");
					logindetail.setUserAgent("");
					logindetail.setUserAgent_version("");
					logindetail.setIpaddress("");
					System.out.println("___LOGIN_11__" + logindetail.getLoginID());
					this._session.getCurrentSession().save(logindetail);
				}
				return uk;
			} else
				return null;
		}

	}

	public String MD5(String md5) {
		StringBuffer sb = new StringBuffer();
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());

			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			System.out.println("___Md5___" + sb.toString());
			// return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return sb.toString();
	}

	@Override
	@Transactional
	public Map<String, Object> AddUser(User user, String usertype) {
		System.out.println("___AddUser___" + user + "______" + usertype);
		Map<String, Object> hmRes = new HashMap<String, Object>();
		String to = user.getEmailid();
		Criteria criteria = _session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("emailid", user.getEmailid()));
		criteria.add(Restrictions.ne("username", user.getUsername()));
		criteria.setProjection(Projections.projectionList().add(Projections.property("emailid")));
		List mail = criteria.list();
		System.out.print("==============>>>>mail" + mail);
		String email = mail.toString();
		int len1 = email.length();
		email = email.substring(1, len1 - 1);

		if (usertype.equals("update")) {
			System.out.println("==usertype===" + usertype);
			if (email == null && email.length() == 0) {
				hmRes.put("status", "1");// update
			} else {
				try {

					User ob = GetUserdetails(user.getUsername());
					if (ob != null) {
						ob.setName(user.getName());
						ob.setMobileno(user.getMobileno());
						ob.setEmailid(user.getEmailid());
						ob.setType(user.getType());
						ob.setStatus(user.getStatus());
						this._session.getCurrentSession().update(ob);
						hmRes.put("data", ob);
						hmRes.put("status", "1");// update
					}

				} catch (Exception ex) {
					hmRes.put("status", "Error == > " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		} else {

			if (to.equals(email)) {
				hmRes.put("status", "0");
			} else {
				User obj = null;

				if (email.isEmpty()) {

					Criteria criteria1 = _session.getCurrentSession().createCriteria(User.class);
					criteria1.add(Restrictions.eq("username", user.getUsername()));
					criteria1.setProjection(Projections.projectionList().add(Projections.property("username")));
					List login = criteria1.list();

					if (!login.isEmpty() && usertype.equals("save")) {
						System.out.println("==usertype===" + usertype + "____" + login.isEmpty());
						hmRes.put("status", "3");
					} else {
						System.out.println("__OTHER___new user save___");
						try {
							int icont = this._session.getCurrentSession().createCriteria(User.class)
									.add(Restrictions.eq("username", user.getUsername())).list().size();
							if (icont == 0) {
								HashMap<String, String> emailconfig = getallEmailconfig();
								String type = "New User";
								HashMap typeofuser = mailType(type);
								String subject = (String) typeofuser.get("subject");
								subject = subject.trim();
								String message = (String) typeofuser.get("message");
								String firstname = user.getName();
								String password = user.getPassword();
								String loginID = user.getUsername();
								message = message.replace("@firstname", firstname);
								message = message.replace("@password", password);
								message = message.replace("@username", loginID);
								// message = message.replace("@@url",
								// emailconfig.get("URL"));
								mailSend(emailconfig.get("EMAIL"), to, emailconfig.get("BCC"),
										emailconfig.get("CCeMail"), subject, message);
								user.setPassword(MD5(user.getPassword()));
								this._session.getCurrentSession().save(user);
								hmRes.put("data", user);
								hmRes.put("status", "1");
							} else {
								hmRes.put("status", "3");
							}
						} catch (Exception ex) {
							this._session.getCurrentSession().clear();
							hmRes.put("status", "Error == > " + ex.getMessage());
							ex.printStackTrace();
						}
					}
				}
			}
		}
		System.out.println("___hmRes____" + hmRes);
		return hmRes;
	}

	public User GetUserdetails(String loginusername) {
		System.out.println("____Getuserdetail___" + loginusername);

		return this._session.getCurrentSession().get(User.class, loginusername);
	}

	@Override
	@Transactional
	public String deleteUser(String loginid) {
		Object persistentInstance = _session.getCurrentSession().load(User.class, loginid);
		_session.getCurrentSession().delete(persistentInstance);
		return "1";
	}

	@Override
	@Transactional
	public User EditUser(String loginid) {

		User userobj = _session.getCurrentSession().get(User.class, loginid);

		return userobj;

	}

	@Transactional
	public String forgotPassword(String email_address) {
		System.out.println("____forgotPassword_email address____" + email_address);
		User userdetail = new User();

		boolean valid = EmailValidator.getInstance().isValid(email_address);

		System.out.println("===AA====" + valid);

		if (valid == true) {

			RandomNumber randomnumber = new RandomNumber();
			String random = getRandomText();
			String temp_password = String.valueOf(get4DigitRandomNumber());
			randomnumber.setIdrandomnumber(random);
			randomnumber.setEmailaddress(email_address);
			System.out.println(randomnumber.getEmailaddresss() + "+++++" + randomnumber.getIdrandomnumber());
			System.out.println("===AA==============Start==================");
			//
			_session.getCurrentSession().getTransaction().begin();
			this._session.getCurrentSession().save(randomnumber);
			_session.getCurrentSession().getTransaction().commit();
			//
			System.out.println("===AA==============End=================");
			Criteria criteria1 = _session.getCurrentSession().createCriteria(User.class);
			criteria1.add(Restrictions.eq("emailid", email_address));
			List email = criteria1.list();

			HashMap hm = null;

			ListIterator itr = email.listIterator();

			while (itr.hasNext()) {
				User obj = (User) itr.next();

				if (email_address.equals(obj.getEmailid())) {

					criteria1.setProjection(Projections.projectionList().add(Projections.property("password")));
					String password = criteria1.list().toString();
					int len = password.length();
					password = password.substring(1, len - 1);

					criteria1.setProjection(Projections.projectionList().add(Projections.property("username")));
					String loginID = criteria1.list().toString();
					int len1 = loginID.length();
					loginID = loginID.substring(1, len1 - 1);

					criteria1.setProjection(Projections.projectionList().add(Projections.property("name")));
					String firstname = criteria1.list().toString();
					int len2 = firstname.length();
					firstname = firstname.substring(1, len2 - 1);

					HashMap<String, String> emailconfig = getallEmailconfig();
					String from = emailconfig.get("EMAIL");
					String cc = null;
					String to = email_address;
					String bcc = null;
					String type = "Forget Password";

					HashMap typeofuser = mailType(type);

					updatepassword(random, temp_password);

					String subject = (String) typeofuser.get("subject");
					String message = (String) typeofuser.get("message");
					message = message.replace("@Firstname", firstname);
					message = message.replace("@password", temp_password);
					message = message.replace("@@username", loginID);
					message = message.replace("@@random", random);
					message = message.replace("@@url", emailconfig.get("URL") + "/resetpassword/" + random);

					try {
						mailSend(from, to, bcc, cc, subject, message);
						return "Success! Password successfully sent to your e-mail address.";
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		} // ifbooleanclosing
		else if (valid == false) {
			return "invalid mail";
		}

		return "mailid doesn't match";

	}

	@Transactional
	public String updatepassword(String randomnumber, String password) {

		System.out.println("___updatepassword___" + randomnumber + "____pwd__" + password);

		Criteria criteria2 = _session.getCurrentSession().createCriteria(RandomNumber.class);
		criteria2.add(Restrictions.eq("idrandomnumber", randomnumber));
		criteria2.setProjection(Projections.projectionList().add(Projections.property("emailaddresss")));
		List email1 = criteria2.list();
		String email = email1.toString();
		int len1 = email.length();
		email = email.substring(1, len1 - 1);

		Criteria criteria = _session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("emailid", email));
		criteria.setProjection(Projections.projectionList().add(Projections.property("username")));

		List login = criteria.list();
		String loginID = login.toString();
		int len = loginID.length();
		loginID = loginID.substring(1, len - 1);
		System.out.print("====================>>>>" + loginID);
		Object obj = _session.getCurrentSession().get(User.class, loginID);
		User userdetail = (User) obj;
		System.out.print("====================>>>>userdetail" + userdetail);
		userdetail.setPassword(MD5(password));
		_session.getCurrentSession().update(userdetail);

		return "password updated successfully";
	}

	@Override
	@Transactional
	public String changePasssword(String loginid, String oldpassword, String newpassword) {

		System.out.println("__changePasssword_____" + loginid + "____" + oldpassword + "__" + newpassword);
		Criteria criteria = _session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", loginid));
		List<User> pass = criteria.list();

		if (pass.size() == 1) {
			User ud = pass.get(0);

			if (ud.getPassword().equals(MD5(oldpassword))) {

				Object obj = _session.getCurrentSession().get(User.class, loginid);
				User userdetail = (User) obj;
				userdetail.setPassword(MD5(newpassword));
				_session.getCurrentSession().update(userdetail);
				return "1";

			} else {
				return "0";
			}

		} else {

			return "2";
		}

	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllUserDetails() {
		System.out.println("__getAllUserDetails___");
		return this._session.getCurrentSession().createCriteria(User.class).list();
	}

	public String mailSend(String from, String to, String bcc, String cc, String subject, String message)
			throws MessagingException {

		ContactUsMails contactusmails = new ContactUsMails();
		CryptoLibrary cry = new CryptoLibrary();
		HashMap<String, String> emailconfig1 = getallEmailconfig();
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", emailconfig1.get("SMTP_HOST"));
		// prop.put("mail.smtp.port", emailconfig1.get("PORT"));
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.mime.address.strict", "false");
		String pass = cry.decrypt(emailconfig1.get("PASSWORD"));
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			}
		});

		try {

			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			if (bcc != null) {
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
			}
			if (cc != null) {
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));

			}

			msg.setSubject(subject);
			msg.setContent(message, "text/html; charset=utf-8");
			Transport.send(msg);
			contactusmails.setStatus("success");
			String firstname = null;
			saveIntoContactUsMails(contactusmails, firstname, subject, message, from, to, cc, bcc);

		} catch (MessagingException e) {
			contactusmails.setStatus("fail");
			e.printStackTrace();

		}

		return "mail sent";

	}

	private void saveIntoContactUsMails(ContactUsMails contactusmails, String firstname, String subject, String message,
			String from, String to, String cc, String bcc) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		this._session.getCurrentSession().createCriteria(ContactUsMails.class);
		contactusmails.setId(contactusmails.getId());
		contactusmails.setMaileduser(firstname);
		contactusmails.setSubject(subject);
		contactusmails.setMessage(message);
		contactusmails.setStatus("success");
		contactusmails.setUsermailid(from);
		contactusmails.setTomailid(to);
		contactusmails.setCcmailids(cc);
		contactusmails.setBccmail(bcc);
		contactusmails.setMaileddate(date);

		contactusmails.setUsermaileddate(date);

		System.out.println("____CONTACT__" + contactusmails.getId());
		_session.getCurrentSession().save(contactusmails);

	}

	public String getSubjectMessage(String subject, String message) throws MessagingException {

		HashMap<String, String> hm = getallEmailconfig();
		String from = hm.get("EMAIL");
		String cc = hm.get("CCeMail");
		String to = hm.get("To");
		String bcc = null;
		return mailSend(from, to, bcc, cc, subject, message);

	}

	public HashMap<String, String> getallEmailconfig() {
		HashMap<String, String> hm = new HashMap<String, String>();

		List<EmailConfig> list = _session.getCurrentSession().createCriteria(EmailConfig.class).list();

		for (int i = 0; i < list.size(); i++) {
			EmailConfig ec = list.get(i);
			hm.put(ec.getAttribute(), ec.getValue());
		}

		return hm;
	}

	public String getRandomText() {

		SecureRandom sec = new SecureRandom();
		BigInteger bi = new BigInteger(50, sec);
		String random = bi.toString(16);
		return random;
	}

	public int get4DigitRandomNumber() {
		int upperLimit = 1000;
		int lowerLimit = 9999;

		int s = getRandomNumber(upperLimit);
		if (s < lowerLimit) {
			s += lowerLimit;
		}
		return s;
	}

	public int getRandomNumber(int maxLimit) {
		return (int) (Math.random() * maxLimit);
	}

	public HashMap mailType(String type) {

		System.out.print("==============>>>>Test"+type);
		Criteria criteria1 = _session.getCurrentSession().createCriteria(EmailTemplate.class);
		criteria1.add(Restrictions.eq("type", type));
		criteria1.setProjection(
				Projections.projectionList().add(Projections.property("subject")).add(Projections.property("message")));
		List list = criteria1.list();
		HashMap hm = null;
		ArrayList al = new ArrayList();
		ListIterator itr = list.listIterator();
		while (itr.hasNext()) {
			hm = new HashMap();
			Object[] obj = (Object[]) itr.next();
			hm.put("subject", obj[0]);
			hm.put("message", obj[1]);
			al.add(hm);
		}
		return hm;
	}

}
