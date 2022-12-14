package client;

import common.ChatIF;
import common.MissionPack;

public class ClientController implements ChatIF {

	private static MissionPack response;
	ClientHandler client;

	public ClientController(final String host, final int port) {
		try {
			client = new ClientHandler(host, port, this);
		} catch (Exception exception) {
			System.out.println("Error: Can't setup connection! Terminating client.");
			System.exit(1);
		}
	}

	public void accept(final Object str) {
		//System.out.println("in accept");
		client.handleMessageFromClientUI(str);
	}

	@Override
	public void display(final String message) {
		System.out.println("> " + message);
	}

	public MissionPack getResponseFromServer() {
		return response;
	}

	public static void setResponseFromServer(final MissionPack obj) {
		ClientController.response = obj;
	}

}
