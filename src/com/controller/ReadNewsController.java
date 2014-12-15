package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Document;

/**
 * @author
 * 
 */
@Controller
@RequestMapping(value = "/")
public class ReadNewsController {
	@Autowired
	private HttpServletRequest request;

	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/read_s")
	public String readSuggestedNews(Map<String, Object> model,
			HttpSession session) {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";

			String id = request.getParameter("id");
			List<Document> suggustedResult = (List<Document>) session
					.getAttribute("suggustedResult");
			if (id != null && suggustedResult != null) {

				for (Document document : suggustedResult) {
					if (document.getId() == Integer.parseInt(id)) {
						String newsContent = document.getBodyFullText();
						String title = document.getTitle();
						request.setAttribute("title", title);
						request.setAttribute("newsContent", newsContent);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Read";
	}

	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/read_n")
	public String readNews(Map<String, Object> model, HttpSession session) {
		if (session.getAttribute("email") == null)
			return "redirect:login";
		try {
			String id = request.getParameter("id");
			List<Document> suggustedResult = (List<Document>) session
					.getAttribute("result");
			if (id != null && suggustedResult != null) {

				for (Document document : suggustedResult) {
					if (document.getId() == Integer.parseInt(id)) {
						String newsContent = document.getBodyFullText();
						String title = document.getTitle();
						request.setAttribute("title", title);
						request.setAttribute("newsContent", newsContent);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Read";
	}

	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/read_r")
	public String readRelNews(Map<String, Object> model, HttpSession session) {
		if (session.getAttribute("email") == null)
			return "redirect:login";
		try {
			String id = request.getParameter("id");
			List<Document> suggustedResult = (List<Document>) session
					.getAttribute("relList");
			if (id != null && suggustedResult != null) {

				for (Document document : suggustedResult) {
					if (document.getId() == Integer.parseInt(id)) {
						String newsContent = document.getBodyFullText();
						String title = document.getTitle();
						request.setAttribute("title", title);
						request.setAttribute("newsContent", newsContent);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Read";
	}

}
