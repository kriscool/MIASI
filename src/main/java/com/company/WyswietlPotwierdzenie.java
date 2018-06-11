package com.company;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.company.db.DB;
import com.company.db.Films;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WyswietlPotwierdzenie implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		DB datab = new DB();
		List<Films> filmList = datab.getAllFilms();
		for(Films f:filmList){
			if(f.getNazwa_filmu().equals(WindowController.film)){
				f.setWolne_miejsca(f.getWolne_miejsca()-WindowController.numberTicket);
				datab.updateFilm(f);
			}
		}
		 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         Button btn = new Button();
         VBox dialogVbox = new VBox(20);
         btn.setText("Zamknij");
         btn.setLayoutX(150);
         btn.setLayoutY(100);
         Text t = new Text("Zamówienie potwierdzone");
         t.setFont(new Font(25));
         dialogVbox.getChildren().add(t);
         dialogVbox.getChildren().add(btn);
         Scene dialogScene = new Scene(dialogVbox, 300, 200);
         dialog.setScene(dialogScene);
         dialog.show();
         
         btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 sendToken(WindowController.email);
                 System.exit(1);
             }
         });
	}
	
	
	public void sendToken(String email) {
		final String username = "transport.projekt.spring@gmail.com";
		final String password = "Drzewce123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("transport.projekt.spring@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Testing Subject");
			message.setText("Potwierdzenie zamówienia"+Integer.toString(WindowController.numberTicket) +" biletów do kina na film "+WindowController.film);
			
			
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
