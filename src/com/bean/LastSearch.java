package com.bean;

public class LastSearch extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4222148759956915765L;
	private String word;
	public LastSearch() {
		// TODO Auto-generated constructor stub
	}

	public LastSearch(User user, String word) {
		
		this.user = user;
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	private User user;

}
