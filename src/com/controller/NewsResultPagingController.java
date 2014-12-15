package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.properties.MessageAndPropertiesDisplay;

/**
 * @author paging for resulted news
 */
@Controller
@RequestMapping(value = "/")
public class NewsResultPagingController {
	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/newsresult_n")
	public String newsSearchNext(Map<String, Object> model, HttpSession session)
			throws Exception {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";

			int size = ((List) session.getAttribute("result")).size();

			if (((String) session.getAttribute("btnValueNews")).equals("Next")) {
				session.setAttribute("btnValueNews1", "Previous");
				session.setAttribute("newsStartIndex",
						(Integer) session.getAttribute("newsEndIndex"));
				session.setAttribute("newsEndIndex",
						(Integer) session.getAttribute("newsEndIndex")
								+ MessageAndPropertiesDisplay.newspages);
				if ((Integer) session.getAttribute("newsEndIndex") >= size) {
					session.setAttribute("btnValueNews", "Next Disabled");
					session.setAttribute("btnValueNews1", "Previous");
					session.setAttribute("newsEndIndex", size);
				}
			}
		} catch (Exception e) {
		}
		return "redirect:search";
	}

	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/newsresult_p")
	public String newsSearchPrevious(Map<String, Object> model,
			HttpSession session) throws Exception {
		try{
		if (session.getAttribute("email") == null)
			return "redirect:login";
		int size = ((List) session.getAttribute("result")).size();

		if (((String) session.getAttribute("btnValueNews1")).equals("Previous")) {
			session.setAttribute("btnValueNews", "Next");
			session.setAttribute("newsEndIndex",
					(Integer) session.getAttribute("newsStartIndex"));
			session.setAttribute("newsStartIndex",
					(Integer) session.getAttribute("newsStartIndex")
							- MessageAndPropertiesDisplay.newspages);
			if ((Integer) session.getAttribute("newsStartIndex") <= 0) {
				session.setAttribute("btnValueNews1", "Previous Disabled");
				session.setAttribute("btnValueNews", "Next");
				session.setAttribute("newsStartIndex", 0);
			} else {
				session.setAttribute("btnValueNews", "Next");
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:search";

	}

}
