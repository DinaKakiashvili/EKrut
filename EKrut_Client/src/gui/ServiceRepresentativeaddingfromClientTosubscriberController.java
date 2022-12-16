package gui;


import java.io.IOException;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServiceRepresentativeaddingfromClientTosubscriberController implements EventHandler<WindowEvent> {

    @FXML
    private TextField IDofcustomer;
    @FXML
   	private Label statusLabel2;
    @FXML
   	private Label statusLabel3;
    @FXML
    private Button logOut;

    @FXML
    private Button changeToSubscriber;

    @FXML
    private Button back;
    @FXML
    private TextArea userLog;

    

    public void start(Stage primaryStage) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/ServiceRepresentativeaddingfromClientTosubscriberscreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut Service Representative change customer to subscriber page");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
	}
    
    
    
    @FXML
	void Backtofirst( ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
		ServiceRepresentativeScreen openScreen1 = new ServiceRepresentativeScreen();
		openScreen1.start(primaryStage);
	}

    @FXML
    void changeToSubscriber(ActionEvent event) {
    	ClientMissionHandler.fromCustomerToSubscriber(this.IDofcustomer,this.statusLabel2,statusLabel3);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
    	ClientLoginScreenController subController = new ClientLoginScreenController(); 
		subController.start(primaryStage);
    }

	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
