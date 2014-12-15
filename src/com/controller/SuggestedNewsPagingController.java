package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.properties.MessageAndPropertiesDisplay;

/**
 * @author
 * 
 */
@Controller
@RequestMapping(value = "/")
public class SuggestedNewsPagingController {
	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/snews_n")
	public String searchnext(Map<String, Object> model, HttpSession session)
			throws Exception {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";
			int size = ((List) session.getAttribute("suggustedResult")).size();
			if (((String) session.getAttribute("btnValue")).equals("Next")) {
				session.setAttribute("btnValue1", "Previous");
				session.setAttribute("sugStartIndex",
						(Integer) session.getAttribute("sugEndIndex"));
				session.setAttribute("sugEndIndex",
						(Integer) session.getAttribute("sugEndIndex")
								+ MessageAndPropertiesDisplay.suggestedpages);
				if ((Integer) session.getAttribute("sugEndIndex") >= size) {
					session.setAttribute("btnValue", "Next Disabled");
					session.setAttribute("btnValue1", "Previous");
					session.setAttribute("sugEndIndex", size);
				} else {
					session.setAttribute("btnValue1", "Previous");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:search";
	}

	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/snews_p")
	public String searchprevious(Map<String, Object> model, HttpSession session)
			throws Exception {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";
			int size = ((List) session.getAttribute("suggustedResult")).size();

			if (((String) session.getAttribute("btnValue1")).equals("Previous")) {
				session.setAttribute("btnValue", "Next");
				session.setAttribute("sugEndIndex",
						(Integer) session.getAttribute("sugStartIndex"));
				session.setAttribute("sugStartIndex",
						(Integer) session.getAttribute("sugStartIndex")
								- MessageAndPropertiesDisplay.suggestedpages);
				if ((Integer) session.getAttribute("sugStartIndex") <= 0) {
					session.setAttribute("btnValue1", "Previous Disabled");
					session.setAttribute("btnValue", "Next");
					session.setAttribute("sugStartIndex", 0);
				} else {
					session.setAttribute("btnValue", "Next");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:search";
	}

}
