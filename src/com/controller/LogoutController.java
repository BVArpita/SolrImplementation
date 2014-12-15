package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 
 * logout class
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController {
	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(Map<String, Object> model, HttpSession session) {
		try{
		session.invalidate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:login";
	}

}
