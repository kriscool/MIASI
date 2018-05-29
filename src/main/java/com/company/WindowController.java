package com.company;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import com.company.db.DB;
import com.company.db.Films;

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
	@FXML
	ChoiceBox filmChoiceBox;
	@FXML
	Text filmChoiceText;
	
	
	private  ProcessEngine processEngine;
	private  ProcessEngineConfiguration cfg;
	private  RepositoryService repositoryService;
	private   Deployment deployment;
	private  ProcessDefinition processDefinition;
	private DB db = new DB();
	public void initialize(){
		cfg = new StandaloneProcessEngineConfiguration()
			      .setJdbcUrl("jdbc:postgresql://localhost:5432/miasi?characterEncoding=utf-8")
			      .setJdbcUsername("postgres")
			      .setJdbcPassword("postgres")
			      .setJdbcDriver("org.postgresql.Driver")
			      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		processEngine = cfg.buildProcessEngine();
		repositoryService = processEngine.getRepositoryService();
		
		deployment = repositoryService.createDeployment()
		        .addClasspathResource("diagrams/process.bpmn").deploy();
		
		processDefinition = repositoryService.createProcessDefinitionQuery()
		        .deploymentId(deployment.getId()).singleResult();
		
		for(int i=1;i<10;i++){
			ticketNumberChoiceBox.getItems().add(i);
        }
		
		List<Films> listfilms = db.getFilms();
		for(Films f : listfilms){
			filmChoiceBox.getItems().add(f.getNazwa_filmu());
		}
		
		
	}
	
	 @FXML
	  private void orderTickets(){
		 /*if(true) {
		 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         VBox dialogVbox = new VBox(20);
         dialogVbox.getChildren().add(new Text("This is a Dialog"));
         Scene dialogScene = new Scene(dialogVbox, 300, 200);
         dialog.setScene(dialogScene);
         dialog.show();*/
		 hideShowItems();
		
		 //}
	
	
		 confirmText.setText( processDefinition.getName());
         
	 }
	 
	 private void hideShowItems(){
		 numberTicketText.setVisible(false);
		 emailText.setVisible(false);
		 orderTicketsButton.setVisible(false);
		 emailTextField.setVisible(false);
		 ticketNumberChoiceBox.setVisible(false);
		 filmChoiceText.setVisible(false);	 
		 filmChoiceBox.setVisible(false);
		 
		 
		 confirmText.setVisible(true);
		 cancelOrderButton.setVisible(true);
		 confirmOrderButton.setVisible(true);
	 }
	 @FXML
	  private void cancelOrder(){
		 System.exit(1);
	 }
	 
	 @FXML
	  private void confirmOrder(){
		 System.exit(1);
	 }
	
}
