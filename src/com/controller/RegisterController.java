package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backend.task.ITaskExecutorManager;
import com.backend.template.MailTemplate;
import com.backend.template.RegistartionMailTemplate;
import com.bean.User;
import com.dao.IGenericBackEndDao;
import com.properties.MessageAndPropertiesDisplay;

/**
 * @author
 * 
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	@Autowired
	private IGenericBackEndDao<User> backEnddao;

	public IGenericBackEndDao<User> getBackEnddao() {
		return backEnddao;
	}

	public void setBackEnddao(IGenericBackEndDao<User> backEnddao) {
		this.backEnddao = backEnddao;
	}

	@Autowired
	private ITaskExecutorManager<MailTemplate> taskExecutorManager;

	public ITaskExecutorManager<MailTemplate> getTaskExecutorManager() {
		return taskExecutorManager;
	}

	public void setTaskExecutorManager(
			ITaskExecutorManager<MailTemplate> taskExecutorManager) {
		this.taskExecutorManager = taskExecutorManager;
	}

	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model,
			HttpSession session) {
		try {
			if (session.getAttribute("email") != null)
				return "redirect:search";
			User userForm = new User();
			model.put("userForm", userForm);
			/**** Preparing country list for nationality drop down in view ***/
			List<String> countries = countryList();
			model.put("countries", countries);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Registration";
	}

	/**
	 * @param user
	 * @param model
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") User user,
			Map<String, Object> model) throws Exception {
		List<String> countries = countryList();
		model.put("countries", countries);
		/**** form validation ***/
		user.setStatus(true);
		if (user.getFirstName() == null || user.getFirstName().equals("")) {
			model.put("error", "First name can't be empty");
			return "Registration";
		}
		if (user.getLastName() == null || user.getLastName().equals("")) {
			model.put("error", "Last name can't be empty");
			return "Registration";
		}
		if (user.getEmail() == null || user.getEmail().equals("")) {
			model.put("error", "Email can't be empty!");
			return "Registration";
		} else if (!validateEmailAddress(user.getEmail())) {
			System.out.println("Email" + user.getEmail());
			model.put("error", "Email is not valid");
			return "Registration";
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			model.put("error", "Password can't be empty");
			return "Registration";
		}
		if (user.getRePassword() == null || user.getRePassword().equals("")) {
			model.put("error", "Re-Password can't be empty");
			return "Registration";
		}
		if (!user.getPassword().equals(user.getRePassword())) {
			model.put("error", "Password does not match");
			return "Registration";
		}
		if (user.getGender() == null || user.getGender().equals("")) {
			model.put("error", "Choose gender");
			return "Registration";
		}
		/**** form validation ***/
		String authCode = String.valueOf(Math.random()).replace(".", "");
		user.setAuthCode(authCode);
		List<User> userList = backEnddao.getEntityList(User.class);
		for (User user2 : userList) {
			if (user.getEmail().equals(user2.getEmail())) {
				model.put("error", "email already taken.");
				return "Registration";
			}
		}
		boolean isRegistered = backEnddao.addEntity(user);
		if (isRegistered) {
			model.put("msg", MessageAndPropertiesDisplay.regsitrationMsg
					.replace("email", user.getEmail()).trim());
			String content = MessageAndPropertiesDisplay.link
					.replace("userid", String.valueOf(user.getId()))
					.replace("code", user.getAuthCode())
					.replace("ipadress", MessageAndPropertiesDisplay.ipaddress)
					.trim();
			RegistartionMailTemplate adminMailTemplate = new RegistartionMailTemplate();
			adminMailTemplate.setToMail(user.getEmail());
			adminMailTemplate.setContent(content);
			Future<MailTemplate> registrationfuture = taskExecutorManager
					.registrationMailTask(adminMailTemplate);
		} else {
			model.put("msg", "Registration Failed!");
		}
		return "RegistrationSuccess";
	}

	/**
	 * @return List<String>
	 */
	private List<String> countryList() {
		List<String> countries = new ArrayList<String>();
		countries.add("India");
		countries.add("USA");
		countries.add("UK");
		countries.add("Canada");
		countries.add("Pakistan");
		return countries;
	}

	/**
	 * @param email
	 * @return boolean
	 */
	private boolean validateEmailAddress(String email) {
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}