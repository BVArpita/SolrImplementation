package com.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Document;
import com.bean.LastSearch;
import com.bean.User;
import com.bean.UserSerachedNews;
import com.dao.IGenericBackEndDao;
import com.dao.IGenericUserBackEndDao;
import com.properties.MessageAndPropertiesDisplay;
import com.utility.SearchData;

/**
 * @author
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class UserLoginController {

	@Autowired
	private IGenericUserBackEndDao<User> backEnddao;

	public IGenericUserBackEndDao<User> getBackEnddao() {
		return backEnddao;
	}

	public void setBackEnddao(IGenericUserBackEndDao<User> backEnddao) {
		this.backEnddao = backEnddao;
	}

	@Autowired
	private IGenericBackEndDao<UserSerachedNews> backEnddao1;

	public IGenericBackEndDao<UserSerachedNews> getBackEnddao1() {
		return backEnddao1;
	}

	public void setBackEnddao1(IGenericBackEndDao<UserSerachedNews> backEnddao1) {
		this.backEnddao1 = backEnddao1;
	}

	/**
	 * @param user
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute("userForm") User user,
			Map<String, Object> model, HttpSession session) throws Exception {
		try {
			if (session.getAttribute("email") != null)
				return "redirect:login";
			if (user.getEmail() == null || user.getEmail().equals("")) {
				model.put("error", "email required");
				return "Login";
			}
			if (user.getPassword() == null || user.getPassword().equals("")) {
				model.put("error", "password required");
				return "Login";
			}
			User user1 = backEnddao.login(user, User.class);
			if (user1 != null) {
				boolean status = user1.isStatus();
				if (!status) {
					model.put("error",
							"account is not active, please contact to admin");
					return "Login";
				}
				if (status) {
					session.setAttribute("email", user1.getEmail());
					session.setAttribute("name", user1.getFirstName());
					session.setAttribute("uid", user1.getId());
					Set<UserSerachedNews> userNewsList = user1.getUserNews();
					session.setAttribute("userNewsList", userNewsList);
					/*** suggested news fetching from solr ***/
					if (userNewsList != null && userNewsList.size() > 0) {
						Iterator<UserSerachedNews> itr = userNewsList
								.iterator();

						String word = itr.next().getWord();
						word = word.trim();
						String wordarr[] = word.split(" ");
						word = "";
						for (String string : wordarr) {
							string = string.trim();
							if (!string.equals("")) {
								string = string.trim();
								word = string.replaceAll("[^a-zA-Z0-9]+", "")
										.trim() + " " + word;
							}
						}

						word = word.trim();
						word = word.replace(" ", "*+or+*").trim();
						if (!word.equals(""))
							word = "*" + word + "*";
						word = word.replace("+**+or+", "").trim();
						word = word.trim();
						word = MessageAndPropertiesDisplay.webservice.replace(
								"word", word).replace("newscount",
								MessageAndPropertiesDisplay.sug_n_c);
						System.out.println("sug:" + word);
						List<Document> suggustedResult = SearchData
								.getData(word);
						session.setAttribute("suggustedResult", suggustedResult);
						/*** paging setting for sugested news ***/
						session.setAttribute("sugStartIndex", 0);
						session.setAttribute("sugEndIndex",
								MessageAndPropertiesDisplay.suggestedpages);
						session.setAttribute("btnValue", "Next");
						session.setAttribute("btnValue1", "Previous Disabled");
						if (MessageAndPropertiesDisplay.suggestedpages >= suggustedResult
								.size()) {
							session.setAttribute("btnValue", "");
							session.setAttribute("btnValue1", "");
						}
						/*** end paging setting for sugested news ***/
					}
					/*** suggested news fetching from solr ***/
					// giving parameter to searchWord to get last search results
					Set<LastSearch> lastSearchs = user1.getLastSearch();
					if (lastSearchs != null && lastSearchs.size() > 0) {
						String word = null;
						Object obj = getLastElement(lastSearchs);
						if (obj != null)
							word = ((LastSearch) obj).getWord();
						System.out.println(word + ":::");
						return "redirect:search?searchWord=" + word;
					} else {
						return "redirect:search";
					}
				} else {
					model.put("msg", "User not yet activated");
					return "Login";
				}
			} else {

				model.put("msg", "Wrong credential");
				return "Login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return "Login";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(Map<String, Object> model, HttpSession session) {
		if (session.getAttribute("email") != null)
			return "redirect:search";
		User userForm = new User();
		model.put("userForm", userForm);

		return "Login";
	}

	public Object getLastElement(final Collection c) {
		final Iterator itr = c.iterator();
		Object lastElement = itr.next();
		while (itr.hasNext()) {
			lastElement = itr.next();
		}
		return lastElement;
	}

}
