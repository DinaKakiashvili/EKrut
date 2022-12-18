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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConfigurationScreenController implements EventHandler<WindowEvent>  {

    @FXML
    private Button backBtn;

    @FXML
    private Button letsGoButton;

    @FXML
    private CheckBox localOrder;

    @FXML
    private ComboBox<String> machineCombobox;

    @FXML
    private CheckBox remoteOrder;
    
    int numOfLocalClicks=0;
    
    int numOfRemoteClicks=0;
       
    public void start(Stage primaryStage) throws IOException {
    	//System.out.println("start");
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/configurationScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut customer page");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
		
	}
    
    @FXML
 	public void initialize() {
    	machineCombobox.getItems().addAll("KIRYAT_MOTZKIN","EILAT","TIRAT_HACARMEL","TZFAT","TEL_AVIV","BEER_SHEVA","RAMAT_GAN");
    	machineCombobox.setDisable(true);
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	final Stage primaryStage = new Stage();
    	MainCustomerPageController mainCustomerPageController = new MainCustomerPageController();
    	mainCustomerPageController.start(primaryStage);
    }
    
    @FXML
    void chooseLocalOrder(ActionEvent event) {
    	numOfLocalClicks++;
    	if(numOfLocalClicks%2==1)
    	{
    		remoteOrder.setDisable(true);
    		machineCombobox.setDisable(false);
    	}	
    	else
    	{
    		remoteOrder.setDisable(false);
    		machineCombobox.setDisable(true);	
    	}
    		
    }

    @FXML
    void chooseRemoteOrder(ActionEvent event) {
    	numOfRemoteClicks++;
    	if(numOfRemoteClicks%2==1)
    	{
    		localOrder.setDisable(true);
    			
    	}    		
    	else
    	{
    		localOrder.setDisable(false);
    	}
    	machineCombobox.setDisable(true);	
    }

    @FXML
    void clickLetsGo(ActionEvent event) throws IOException {
    	if(localOrder.isSelected())
    	{
    		String store=machineCombobox.getValue();
    		ClientMissionHandler.CREATE_LOCAL_ORDER(store,event);
    	}
    		
    }


	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}

}