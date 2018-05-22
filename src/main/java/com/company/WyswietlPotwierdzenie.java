package com.company;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WyswietlPotwierdzenie implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("Potwierdzam");
	}
}
