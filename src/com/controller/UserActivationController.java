package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.User;
import com.dao.IGenericBackEndDao;

/**
 * @author
 * 
 */
@Controller
@RequestMapping(value = "/active")
public class UserActivationController {
	@Autowired
	private IGenericBackEndDao<User> backEnddao;

	public IGenericBackEndDao<User> getBackEnddao() {
		return backEnddao;
	}

	public void setBackEnddao(IGenericBackEndDao<User> backEnddao) {
		this.backEnddao = backEnddao;
	}

	/**
	 * @param user
	 * @param model
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String active(@ModelAttribute("userForm") User user,
			Map<String, Object> model) throws Exception {
		try {
			String msg = "";
			User user1 = backEnddao.getEntity(user.getId(), User.class);
			if (user.getAuthCode().equals(user1.getAuthCode())) {
				user1.setStatus(true);
				backEnddao.updateEntity(User.class, user1, user.getId());
				msg = "Your acount is activated now";
			} else {
				msg = "There is some thing wrong with your account try again or please contact admin";
			}
			model.put("msg", msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Login";

	}

}
