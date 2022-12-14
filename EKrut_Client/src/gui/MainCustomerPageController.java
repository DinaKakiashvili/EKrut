package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainCustomerPageController implements EventHandler<WindowEvent> {

    @FXML
    private Button contactUs;

    @FXML
    private Button createNewOrder;

    @FXML
    private Button logOut;

    @FXML
    private Button manageOrders;

    @FXML
    private TextArea userLog;
    
    public void start(Stage primaryStage) throws IOException {
    	System.out.println("start");
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
    void logOut(ActionEvent event) {

    }

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}



}
