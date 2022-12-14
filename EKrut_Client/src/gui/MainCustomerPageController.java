package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainCustomerPageController implements EventHandler<WindowEvent> {
	
	private static String firstName;
	private static String role;
	private static String phone;

    @FXML
    private Button contactUs;

	@FXML
    private Button createNewOrder;

    @FXML
    public Label hello;

    @FXML
    private Button logOut;

    @FXML
    private Button manageOrders;

    @FXML
    private TextArea userLog;
    
    public static String getFirstName() {
		return firstName;
	}
	public static void setFirstName(String firstName) {
		MainCustomerPageController.firstName = firstName;
	}
	public static String getRole() {
		return role;
	}
	public static void setRole(String role) {
		MainCustomerPageController.role = role;
	}
	public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		MainCustomerPageController.phone = phone;
	}

    @FXML
	public void initialize() {
    	hello.setText("Welcome, "+firstName);
    	userLog.setText("Phone number:"+phone+"\n"+"Account type:"+role);
	}
    public void start(Stage primaryStage) throws IOException {
    	//System.out.println("start");
    	
  
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/MainCustomerPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut customer page");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
		
	}
    
   
    
    @FXML
    void createNewOrder(ActionEvent event) {
    	
    }
    
    @FXML
    void manageOrders(ActionEvent event) {

    }
    

    @FXML
    void contactUs(ActionEvent event) {

    }


    @FXML
    void logOut(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
    	ClientLoginScreenController subController = new ClientLoginScreenController(); 
		subController.start(primaryStage);
		//change isLoggedIn to 0
    }
    

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}



}
