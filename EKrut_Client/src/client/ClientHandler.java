package client;

import java.io.IOException;

import common.ChatIF;
import common.MissionPack;
import ocsf.client.AbstractClient;

public class ClientHandler extends AbstractClient {
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	/**
	 * waiting for response after the client sent the message
	 */
	public static boolean awaitResponse = false;
	/**
	 * The response object that we got from the server after executing the
	 * query/command
	 */
	ChatIF clientUI;

	public ClientHandler(final String host, final int port, final ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
			openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object str) {
		awaitResponse = false;
		if (str instanceof MissionPack) {
			final MissionPack msg = (MissionPack) str;
			ClientController.setResponseFromServer(msg);
			if (msg.getInformation() !=null)
				System.out.println(msg.getInformation().toString());
			clientUI.display(msg.getResponse().toString());
		}
	}

	/**
	 * This method waits for input from the console. Once it is received, it sends
	 * it to the client's message handler.
	 */
	/*public void accept(Object message) {
		handleMessageFromClientUI(message);
	}*/

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
