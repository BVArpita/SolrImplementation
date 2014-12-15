package com.backend.mailmanager;

import java.util.Date;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.backend.template.MailTemplate;

public class MailManager {

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	/**
	 * This method will send mail at the time of successful registration
	 * to the registered user.
	 * @author Vinay Singh Rawat
	 * @param mailTemplate
	 * @return void
	 */
	public void registrationMail(MailTemplate mailTemplate) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
			msg.setTo(mailTemplate.getToMail());
			msg.setText(mailTemplate.getContent());
			mailSender.send(msg);
			mailTemplate.setStatus(true);
			mailTemplate.setDate(new Date());
			mailTemplate.setMsg("Mail send to "+mailTemplate.getToMail()+" on "+new Date());
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
			mailTemplate.setStatus(false);
			mailTemplate.setMsg("Mail failed to "+mailTemplate.getToMail()+" on "+new Date());
			
		}
	}

}
