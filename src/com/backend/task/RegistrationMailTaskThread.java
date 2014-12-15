package com.backend.task;

import java.util.concurrent.Callable;

import com.backend.mailmanager.MailManager;
import com.backend.template.MailTemplate;

public class RegistrationMailTaskThread implements Callable<MailTemplate> {

	private MailManager mailManager;

	private MailTemplate mailTemplate;

	public RegistrationMailTaskThread(MailManager mailManager, MailTemplate mailTemplate) {
		this.mailManager = mailManager;
		this.mailTemplate=mailTemplate;

	}
	@Override
	public MailTemplate call() throws Exception {
		
		try {
		     mailManager.registrationMail(mailTemplate);
			return mailTemplate;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return mailTemplate;
		}
	}

}
