package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import server.ServerConfiguration;

public class ServerUI extends Application {
	static ServerConfiguration sv;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		ServerController serverGui = new ServerController();
		serverGui.start(primaryStage);
	}

	public static void runServer(String p) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(p); // Set port to 5555

		} catch (Throwable t) {
			System.out.println("ERROR - Could not connect!");
		}

		sv = new ServerConfiguration(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR - Could not listen for clients!");
		}
	}

	public static void disconnect() {
		if (sv == null)
			sv.stopListening();
		else
			try {
				sv.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println("Server Disconnected");
	}
}
