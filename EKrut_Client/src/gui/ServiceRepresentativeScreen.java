
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServiceRepresentativeScreen implements EventHandler<WindowEvent>{

    @FXML
    private TextArea userLog;

    @FXML
    private Button AddingCustomer;

    @FXML
    private Button CustomerIntoSubscriber;

    @FXML
    private Button logOut;

    

    public void start(Stage primaryStage) throws IOException {
    	
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceRepresentativeScreen.fxml"));
    
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("E-Krut Service Representative page");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
	}
    
    
    @FXML
    void AddingCustomer(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
    	ServiceRepresentativeController add = new ServiceRepresentativeController();
    	add.start(primaryStage);
    }

    @FXML
    void CustomerIntoSubscriber(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
		ServiceRepresentativeaddingfromClientTosubscriberController screen= new ServiceRepresentativeaddingfromClientTosubscriberController();
		screen.start(primaryStage);
    }

    @FXML
    void logOut(ActionEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
    	ClientLoginScreenController subController = new ClientLoginScreenController(); 
		try {
			subController.start(primaryStage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//change isLoggedIn to 0
    }


	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

