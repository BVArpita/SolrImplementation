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
public class RelatedNewsPagingController {
	/**
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/relnews_n")
	public String relsearchnext(Map<String, Object> model, HttpSession session)
			throws Exception {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";
			if (session.getAttribute("relList") != null) {
				int size = ((List) session.getAttribute("relList")).size();
				if (((String) session.getAttribute("btnValueRelNews"))
						.equals("Next")) {
					session.setAttribute("btnValueRelNews1", "Previous");
					session.setAttribute("rnewsStartIndex",
							(Integer) session.getAttribute("rnewsEndIndex"));
					session.setAttribute("rnewsEndIndex",
							(Integer) session.getAttribute("rnewsEndIndex")
									+ MessageAndPropertiesDisplay.relnewspages);
					if ((Integer) session.getAttribute("rnewsEndIndex") >= size) {
						session.setAttribute("btnValueRelNews", "Next Disabled");
						session.setAttribute("btnValueRelNews1", "Previous");
						session.setAttribute("rnewsEndIndex", size);
					}
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
	@RequestMapping(method = RequestMethod.GET, value = "/relnews_p")
	public String relsearchprevious(Map<String, Object> model,
			HttpSession session) throws Exception {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";
			if (session.getAttribute("relList") != null) {
				int size = ((List) session.getAttribute("relList")).size();

				if (((String) session.getAttribute("btnValueRelNews1"))
						.equals("Previous")) {
					session.setAttribute("btnValueRelNews", "Next");
					session.setAttribute("rnewsEndIndex",
							(Integer) session.getAttribute("rnewsStartIndex"));
					session.setAttribute("rnewsStartIndex",
							(Integer) session.getAttribute("rnewsStartIndex")
									- MessageAndPropertiesDisplay.relnewspages);
					if ((Integer) session.getAttribute("rnewsStartIndex") <= 0) {
						session.setAttribute("btnValueRelNews1",
								"Previous Disabled");
						session.setAttribute("btnValueRelNews", "Next");
						session.setAttribute("rnewsStartIndex", 0);
					} else {
						session.setAttribute("btnValueRelNews", "Next");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:search";
	}

}
