package com.bean;

import java.io.Serializable;
import java.util.Set;


public class User extends UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8715890774878409667L;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String rePassword;
	private String gender;
	private String nationality;
	private String authCode;
	private boolean status;
	private Set<UserSerachedNews> userNews;
	private Set<LastSearch> lastSearch;

	public Set<UserSerachedNews> getUserNews() {
		return userNews;
	}

	public void setUserNews(Set<UserSerachedNews> userNews) {
		this.userNews = userNews;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User() {

	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
		
		
	}

	public Set<LastSearch> getLastSearch() {
		return lastSearch;
	}

	public void setLastSearch(Set<LastSearch> lastSearch) {
		this.lastSearch = lastSearch;
	}

}
