package com.backend.task;

import java.util.concurrent.Future;

import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.ExecutorServiceAdapter;

import com.backend.mailmanager.MailManager;
import com.backend.template.MailTemplate;

public class TaskExecutorManager implements ITaskExecutorManager<MailTemplate> {
	public static Future<MailTemplate> mailFutureObject = null;
	private TaskExecutor taskExecutor;
	private MailManager mailManager;
	/** To get Future object as return we have to use ExecutorServiceAdapter **/
	private ExecutorServiceAdapter eServiceAdapter;
	public TaskExecutorManager(TaskExecutor taskExecutor,
			MailManager mailManager) {
		this.taskExecutor = taskExecutor;
		this.mailManager = mailManager;
		eServiceAdapter = new ExecutorServiceAdapter(taskExecutor);
	}
	
	@Override
	public Future<MailTemplate> registrationMailTask(MailTemplate mailTemplate) throws Exception {
		RegistrationMailTaskThread registrationMailTaskThread = new RegistrationMailTaskThread(mailManager, mailTemplate);
		Future<MailTemplate> maFuture = eServiceAdapter.submit(registrationMailTaskThread);
		TaskExecutorManager.mailFutureObject = maFuture;
		return maFuture;
	}
	
}
