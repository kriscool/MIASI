package com.company;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.company.db.DB;
import com.company.db.Films;

public class WprowadzZamowienie implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
/*		DB datab = new DB();
		Films film = new Films();

		arg0.setVariable("email", "jakup@gmail.com");
		arg0.setVariable("emailCorrect", false);
		arg0.setVariable("available", false);
		arg0.setVariable("confirmation", true);
		
//		film.setNazwa_filmu("Szybcy i wsciekli");
//		film.setWolne_miejsca(200);
//		datab.add(film);
		List<Films> listfilms = datab.getFilms();
		for(Films f : listfilms){
			System.out.println("ID: " +  f.getId() + " Nazwa: " + f.getNazwa_filmu() + " Wolne miejsca: " +f.getWolne_miejsca());

		}*/

	}
}
