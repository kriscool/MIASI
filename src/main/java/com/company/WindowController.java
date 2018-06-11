package com.company;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.activiti.bpmn.model.Task;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

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
	ChoiceBox<String> filmChoiceBox;
	@FXML
	Text filmChoiceText;
	public static int numberTicket;
	public static String film;
	private String filename = "C:\\Users\\kriscool\\workspace-new\\sado_olczag_krzys\\src\\main\\resources\\diagrams\\process.bpmn";
	public static  ProcessEngine processEngine;
	private  ProcessEngineConfiguration cfg;
	private  RepositoryService repositoryService;
	private   Deployment deployment;
	private  ProcessDefinition processDefinition;
	private DB db = new DB();
	private Map<String, Object> variableMap = new HashMap<String, Object>();
	private ProcessInstance processInstance;
	public static String email;
	public static List<Films> listfilms;
	public static String selectedFilm;
	public static Integer ticketAmount;
	public void initialize() throws FileNotFoundException{
		cfg = new StandaloneProcessEngineConfiguration()
			      .setJdbcUrl("jdbc:postgresql://localhost:5432/miasi?characterEncoding=utf-8")
			      .setJdbcUsername("postgres")
			      .setJdbcPassword("postgres")
			      .setJdbcDriver("org.postgresql.Driver")
			      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		processEngine = cfg.buildProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addInputStream("myProcess.bpmn20.xml", new FileInputStream(filename))
				.deploy();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		variableMap.put("name", "Activiti");
		for(int i=1;i<10;i++){
			ticketNumberChoiceBox.getItems().add(i);
        }
		listfilms = db.getAllFilms();
		for(Films f : listfilms){
			filmChoiceBox.getItems().add(f.getNazwa_filmu());
		}
		
		
	}
	

	 @FXML
	  private void orderTickets(){
		 hideShowItems();	 
			variableMap.put("email", emailTextField.getText());
			selectedFilm = filmChoiceBox.getValue();
			ticketAmount = ticketNumberChoiceBox.getValue(); 
		 RuntimeService runtimeService = processEngine.getRuntimeService();
		 processInstance = runtimeService.startProcessInstanceByKey("myProcess", variableMap);
			assertNotNull(processInstance.getId());
			System.out.println("id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
         
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
		 List<org.activiti.engine.task.Task> tasks = processEngine.getTaskService().createTaskQuery().taskDefinitionKey("usertask1").list();
		 variableMap.put("confirmation", false);
		 processEngine.getTaskService().complete(tasks.get(0).getId(), variableMap);
	 }
	 
	 @FXML
	  private void confirmOrder(){
		 numberTicket=ticketNumberChoiceBox.getValue();
		 film=filmChoiceBox.getValue();
		 email=emailTextField.getText();
	
		 List<org.activiti.engine.task.Task> tasks = processEngine.getTaskService().createTaskQuery().taskDefinitionKey("usertask1").list();
		 variableMap.put("confirmation", true);
		 processEngine.getTaskService().complete(tasks.get(0).getId(), variableMap);
		 
	 }
	
}
