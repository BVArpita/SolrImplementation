package com.backend.task;

import java.util.concurrent.Future;

import com.backend.template.MailTemplate;

public interface ITaskExecutorManager<T extends MailTemplate> {
	public Future<T> registrationMailTask(T mailTemplate) throws Exception;

}
