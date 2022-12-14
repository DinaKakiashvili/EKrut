package gui;

import java.io.IOException;

import client.ClientMissionHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientLoginScreenController implements EventHandler<WindowEvent> {

    @FXML
    private TextField UsernameTxt;

    @FXML
    private Button userLogin;

    @FXML
    private TextField passwordTxt;
    
    @FXML
    private Label errorLabel;

    @FXML
    void clickUserLogin(MouseEvent event) throws Exception {
    //	((Node) event.getSource()).getScene().getWindow().hide();
    	System.out.println("clicked login");
		ClientMissionHandler.IDENTIFY_USER(event, UsernameTxt.getText(), passwordTxt.getText(),errorLabel);
    }
    
    
    public void start(Stage primaryStage) throws IOException {
    	System.out.println("start");
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/ClientLoginScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		primaryStage.setOnCloseRequest(this);
	}

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}

}
