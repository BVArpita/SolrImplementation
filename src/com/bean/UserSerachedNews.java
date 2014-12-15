package com.bean;

public class UserSerachedNews extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7030138853021999041L;
	private String word;
	
	private int count;
	private User user;
	public UserSerachedNews() {
		// TODO Auto-generated constructor stub
	}
	public UserSerachedNews(String word, int count, User user) {
		this.word = word;
		this.count = count;
		this.user = user;
	}


	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
