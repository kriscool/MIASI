package application;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowController {
	@FXML
	TextField emailTextField;
	@FXML
	ChoiceBox<Integer> ticketNumberChoiceBox;
	@FXML
	Button orderTicketsButton;
	@FXML
	Button cancelOrderButton;
	@FXML
	Button confirmOrderButton;
	@FXML
	Text numberTicketText;
	@FXML
	Text emailText;
	@FXML
	Text confirmText;
	
	public void initialize(){
		for(int i=1;i<10;i++){
			ticketNumberChoiceBox.getItems().add(i);
        }
	}
	
	 @FXML
	  private void orderTickets(){
		 /*	 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         VBox dialogVbox = new VBox(20);
         dialogVbox.getChildren().add(new Text("This is a Dialog"));
         Scene dialogScene = new Scene(dialogVbox, 300, 200);
         dialog.setScene(dialogScene);
         dialog.show();*/
		 hideShowItems();
         
	 }
	 
	 private void hideShowItems(){
		 numberTicketText.setVisible(false);
		 emailText.setVisible(false);
		 orderTicketsButton.setVisible(false);
		 emailTextField.setVisible(false);
		 ticketNumberChoiceBox.setVisible(false);
				 
		 confirmText.setVisible(true);
		 cancelOrderButton.setVisible(true);
		 confirmOrderButton.setVisible(true);
	 }
	 @FXML
	  private void cancelOrder(){
	 }
	 
	 @FXML
	  private void confirmOrder(){
	 }
	
}
