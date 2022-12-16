
package gui;


import java.io.IOException;

import client.ClientMissionHandler;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServiceRepresentativeController implements EventHandler<WindowEvent>{

    @FXML
    private TextArea userLog;

    @FXML
    private Button logOut;
    
    @FXML
    private Button back;


    @FXML
    private TextField IDText;

    @FXML
    private TextField FirstNameText;

    @FXML
    private TextField LastNameText;

    @FXML
    private TextField PhoneNumberText;

    @FXML
    private TextField EmailAddressText;

    @FXML
    private TextField CreditCardNumberText;

    @FXML
    private Button AddCustomer;
    @FXML
	private Label statusLabel;


    public void start(Stage primaryStage) throws IOException {
    	System.out.println("start");
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceRepresentativeaddingScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut Service Representative Add customer page");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
	}
	
    
    @FXML
    void AddCustomer(ActionEvent event) {
    	ClientMissionHandler.AddingCustomerNew(this.IDText, this.FirstNameText, this.LastNameText,
				this.PhoneNumberText,this.EmailAddressText,this.CreditCardNumberText,this.statusLabel);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
    	ClientLoginScreenController subController = new ClientLoginScreenController(); 
		subController.start(primaryStage);
		//change isLoggedIn to 0
    }
    @FXML
	void Backtofirst( ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
		ServiceRepresentativeScreen openScreen1 = new ServiceRepresentativeScreen();
		openScreen1.start(primaryStage);
	}


	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

