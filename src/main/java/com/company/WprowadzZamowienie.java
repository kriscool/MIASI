package com.company;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WprowadzZamowienie implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		arg0.setVariable("email", "jakup@gmail.com");
	}
}
