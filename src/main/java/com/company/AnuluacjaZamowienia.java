package com.company;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnuluacjaZamowienia implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         Button btn = new Button();
         VBox dialogVbox = new VBox(20);
         btn.setLayoutX(150);
         btn.setLayoutY(100);
         Text t = new Text("B³êdna walidacja");
         t.setFont(new Font(25));
         dialogVbox.getChildren().add(t);
         dialogVbox.getChildren().add(btn);
         Scene dialogScene = new Scene(dialogVbox, 300, 200);
         dialog.setScene(dialogScene);
         dialog.show();
         
         btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.exit(1);
             }
         });
	}
}
