package com.company;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class SprawdzDostepnosc implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		WindowController.processEngine.getRuntimeService().suspendProcessInstanceById(arg0.getProcessInstanceId());
		for (int a = 0; a < WindowController.listfilms.size(); a++) {
			if (WindowController.selectedFilm.equals(WindowController.listfilms.get(a).getNazwa_filmu())) {
				if (WindowController.ticketAmount <= WindowController.listfilms.get(a).getWolne_miejsca()) {
					arg0.setVariable("available", "true");
					break;
				} else {
					arg0.setVariable("available", "false");
					break;
				}
			}
		}
	}
}
