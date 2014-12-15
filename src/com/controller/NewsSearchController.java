package com.controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bean.Document;
import com.bean.Entity;
import com.bean.LastSearch;
import com.bean.SearchForm;
import com.bean.User;
import com.bean.UserEntity;
import com.bean.UserSerachedNews;
import com.dao.IGenericBackEndDao;
import com.dao.IGenericUserBackEndDao;
import com.properties.MessageAndPropertiesDisplay;
import com.utility.SearchData;

/**
 * @author news search will control searching news and get news.
 */
@Controller
@RequestMapping(value = "/search")
public class NewsSearchController {
	@Autowired
	ServletContext context;
	@Autowired
	private IGenericBackEndDao<UserSerachedNews> backEnddao;
	@Autowired
	private IGenericBackEndDao<LastSearch> backEnddao1;
	@Autowired
	private IGenericUserBackEndDao<User> backEnddaouser;

	public IGenericUserBackEndDao<User> getBackEnddaouser() {
		return backEnddaouser;
	}

	public void setBackEnddaouser(IGenericUserBackEndDao<User> backEnddaouser) {
		this.backEnddaouser = backEnddaouser;
	}

	public IGenericBackEndDao<UserSerachedNews> getBackEnddao() {
		return backEnddao;
	}

	public void setBackEnddao(IGenericBackEndDao<UserSerachedNews> backEnddao) {
		this.backEnddao = backEnddao;
	}

	/**
	 * @param searchForm
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String searchDocs(
			@ModelAttribute("searchForm") SearchForm searchForm,
			Map<String, Object> model, HttpSession session) {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";
			String email = (String) session.getAttribute("email");
			String word = searchForm.getSearchWord();
			if (word == null || word.equals(""))
				return "Dashboard";
			word = word.trim();
			String w = word;

			String wordarr[] = word.split(" ");
			word = "";
			for (String string : wordarr) {
				string = string.trim();
				if (!string.equals("")) {
					string = string.trim();
					word = string.replaceAll("[^a-zA-Z0-9]+", "").trim() + " "
							+ word;
				}
			}

			word = word.trim();
			word = word.replace(" ", "*+or+*").trim();
			if (!word.equals(""))
				word = "*" + word + "*";
			word = word.replace("+**+or+", "").trim();
			word = word.trim();
			word = MessageAndPropertiesDisplay.webservice.replace("word", word)
					.replace("newscount", MessageAndPropertiesDisplay.nc);
			List<Document> result = SearchData.getData(word);

			if (result.size() > 0) {
				/*** adding personalization or recommend news for user count ****/
				long uid = (Long) session.getAttribute("uid");
				try {
					boolean wordFound = false;
					UserEntity user = new User();
					System.out.println("UID:" + uid);
					user.setId(uid);
					UserEntity user1 = backEnddao.getEntityByID(user,
							UserEntity.class);
					if (user1 != null && user1 instanceof UserEntity) {
						User u1 = (User) user1;
						System.out.println(user1);
						if (u1 != null && u1.getUserNews() != null) {
							System.out.println("User News size:"
									+ u1.getUserNews().size());
							Set<UserSerachedNews> userSerachedNewsList = u1
									.getUserNews();
							for (UserSerachedNews userSerachedNews : userSerachedNewsList) {
								if (userSerachedNews.getWord().trim()
										.equals(w.trim())) {
									wordFound = true;
									break;
								}
							}
						}
						if (!wordFound) {
							user.setId(uid);
							UserSerachedNews userNews = new UserSerachedNews(w,
									result.size(), u1);
							backEnddao.addEntity(userNews);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				/*** end adding personalization or recommend news for user count ****/
				// for news result pagging perpose start
				session.setAttribute("result", result);
				session.setAttribute("newsStartIndex", 0);
				session.setAttribute("newsEndIndex",
						MessageAndPropertiesDisplay.newspages);
				session.setAttribute("btnValueNews", "Next");
				session.setAttribute("btnValueNews1", "Previous Disabled");
				if (MessageAndPropertiesDisplay.newspages >= result.size()
						|| result.size() == 0) {
					session.setAttribute("btnValueNews", "");
					session.setAttribute("btnValueNews1", "");
				}
				try {
					User user = new User();
					user.setId(uid);
					LastSearch lastSearch = new LastSearch(user, w);
					backEnddao1.addEntity(lastSearch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// for news result pagging perpose end
			if (context.getAttribute(email) != null && result != null
					&& result.size() != 0) {
				Set<String> personalisation = (LinkedHashSet<String>) context
						.getAttribute(email);
				personalisation.add(w + ":" + result.size());

			} else if (context.getAttribute(email) == null && result != null
					&& result.size() != 0) {
				Set<String> personalisation = new LinkedHashSet<String>();
				personalisation.add(w + ":" + result.size());
				context.setAttribute(email, personalisation);
			}
			if (result != null && result.size() != 0) {
				session.setAttribute("word", w);
			} else {
				session.setAttribute("word", "0 result found");
				session.removeAttribute("relList");
			}
			session.setAttribute("result", result);
			if (result.size() >= 9) {
				List<Document> relList = result.subList(result.size() / 6,
						result.size() - 1);
				// for news result pagging perpose start
				if (relList.size() > 0)
					session.setAttribute("relList", relList);
				session.setAttribute("rnewsStartIndex", 0);
				session.setAttribute("rnewsEndIndex",
						MessageAndPropertiesDisplay.relnewspages);
				session.setAttribute("btnValueRelNews", "Next");
				session.setAttribute("btnValueRelNews1", "Previous Disabled");
				if (MessageAndPropertiesDisplay.relnewspages >= relList.size()) {
					session.setAttribute("btnValueRelNews", "");
					session.setAttribute("btnValueRelNews1", "");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		// for news result pagging perpose end
		return "Dashboard";
	}

	/**
	 * @param searchForm
	 * @param model
	 * @param session
	 * @return String (url pattern)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search(@ModelAttribute("searchForm") SearchForm searchForm,
			Map<String, Object> model, HttpSession session) {
		try {
			if (session.getAttribute("email") == null)
				return "redirect:login";

			if (searchForm != null && searchForm.getSearchWord() != null
					&& !searchForm.getSearchWord().equals("")) {
				searchDocs(searchForm, model, session);
			} else {
				return "Dashboard";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "Dashboard";
	}

	public IGenericBackEndDao<LastSearch> getBackEnddao1() {
		return backEnddao1;
	}

	public void setBackEnddao1(IGenericBackEndDao<LastSearch> backEnddao1) {
		this.backEnddao1 = backEnddao1;
	}

}
