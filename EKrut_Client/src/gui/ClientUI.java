package gui;

import client.ClientController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application
{
    public static ClientController chat;
    
    public static void main(final String[] args) throws Exception {
        launch(args);
    }
    
    public void start(final Stage primaryStage) throws Exception {
    	ClientOpeningScreenController cl = new ClientOpeningScreenController();
    	System.out.println("natalie and dina");
    	System.out.println("check");
    	System.out.println("sheker kolshehuuu");
        cl.start(primaryStage);
        
    }
}
