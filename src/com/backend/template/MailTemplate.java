package com.backend.template;

import java.util.Date;

public abstract class MailTemplate {
	private long id;
	private String toMail;
	private String fromMail;
	private Date date;
	private boolean status;
	private String subject;
	private String Content;
	private String[] multipleMailID;
	private String msg;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String[] getMultipleMailID() {
		return multipleMailID;
	}
	public void setMultipleMailID(String[] multipleMailID) {
		this.multipleMailID = multipleMailID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getFromMail() {
		return fromMail;
	}
	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	} 

}
