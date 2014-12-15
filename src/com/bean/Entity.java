package com.bean;

import java.io.Serializable;

public abstract class Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4233709998278181734L;
	private long id;
	public Entity(long id) {
		super();
		this.id = id;
	}
	public Entity() {
		// TODO Auto-generated constructor stub
	}

	private String publishLog;
	public String getPublishLog() {
		return publishLog;
	}

	public void setPublishLog(String publishLog) {
		this.publishLog = publishLog;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

}
