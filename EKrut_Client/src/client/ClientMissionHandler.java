package client;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.List;
import common.*;
import common.MissionPack;
import entities.Subscriber;
import gui.ClientLoginScreenController;
import gui.ClientUI;
import gui.GetSubscribersController;
import gui.MainCustomerPageController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClientMissionHandler {

	public static void CONNECT_TO_SERVER(final MouseEvent event, final String ip, final String port) throws Exception {
		ClientUI.chat = new ClientController(ip, Integer.parseInt(port)); //create the static object of chat in clientUI
		if (ClientUI.chat != null) {
			MissionPack obj = new MissionPack(Mission.SEND_CONNECTION_DETAILS, null, null); //create a mission pack that sends the connection details to the server
			final ArrayList<String> details = new ArrayList<String>();
			details.add(InetAddress.getLocalHost().getHostAddress());
			details.add(InetAddress.getLocalHost().getHostName());
			obj.setInformation(details); //the information is an arraylist that contains the connection details
			//System.out.println("in client");
			ClientUI.chat.accept(obj); 
			/*send the object to the server, from now on it will be taken care of in the server
			 * (->handleMessageFromClient->handleMessages->parsingToData->..QueryExecutor)
			 * an updated object of MissionPack is now in obj and within it the response and needed information
			 */
			obj = ClientUI.chat.getResponseFromServer(); 
			if (obj.getResponse().equals(Response.UPDATE_CONNECTION_SUCCESS)) {
				((Node) event.getSource()).getScene().getWindow().hide();
				final Stage primaryStage = new Stage();
				ClientLoginScreenController subController = new ClientLoginScreenController(); 
				subController.start(primaryStage); //connection successful->goto the next window
			} else {
				System.out.println("did not connect to server");
			}
		}
	}

	public static void GET_SUBSCRIBERS(final ObservableList<Subscriber> listView, final TableView<Subscriber> table,
			final Label statusLabel) {
		MissionPack obj = new MissionPack(Mission.GET_SUBSCRIBERS, null, null);
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse() == Response.FOUND_SUBSCRIBERS) {
			listView.clear();
			List<Subscriber> list = (List<Subscriber>) obj.getInformation();
			for (int i = 0; i < list.size(); ++i) {
				listView.add(list.get(i));
				System.out.println(list.get(i));
			}
			table.setItems(listView);
			statusLabel.setText("Upload Success");
		} else {
			statusLabel.setText("Upload Failed");
		}
	}

	public static void DISCONNECT_FROM_SERVER() {
		final MissionPack obj = new MissionPack(Mission.SEND_DISCONNECTION_DETAILS, null, null);
		final ArrayList<String> details = new ArrayList<String>();
		try {
			details.add(InetAddress.getLocalHost().getHostAddress());
			details.add(InetAddress.getLocalHost().getHostName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.setInformation(details);
		ClientUI.chat.accept(obj);
	}

	public static void EDIT_SUBSCRIBERS(final Label statusLabel, final TextField lblEditCreditCardNumber,
			final TextField lblEditSubscriberNumber, final TextField lblEditId) {
		final Subscriber subscriber = new Subscriber();
		subscriber.setCreditCardNumber(lblEditCreditCardNumber.getText());
		subscriber.setSubscriberNumber(lblEditSubscriberNumber.getText());
		subscriber.setId(lblEditId.getText());

		MissionPack obj = new MissionPack(Mission.EDIT_SUBSCRIBERS_DATA, null, subscriber);
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse() == Response.EDIT_SUBSCRIBERS_FAILD) {
			statusLabel.setText("Edit Failed");
		} else {
			statusLabel.setText("Edit Success");
		}
	}

	public static void IDENTIFY_USER(MouseEvent event, String username, String password, Label errorLabel)
			throws Exception {
		//System.out.println("hooooooooooooooooo");
		String[] data = { username, password };
		MissionPack obj = new MissionPack(Mission.IDENTIFY_USER, null, data); //create mission to login and send login data 
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		String firstName=((String[])obj.getInformation())[4];
		String phone=((String[])obj.getInformation())[3];
		String role=((String[])obj.getInformation())[2];
		
		if (obj.getResponse().equals(Response.LOGIN_SUCCEED)) {
			((Node) event.getSource()).getScene().getWindow().hide();
			final Stage primaryStage = new Stage();
			if (role.equals("customer")) {
				
				MainCustomerPageController.setFirstName(firstName);
				MainCustomerPageController.setPhone(phone);
				MainCustomerPageController.setRole(role);
				MainCustomerPageController subController = new MainCustomerPageController();
				subController.start(primaryStage);			
			}
		} else {
			// System.out.println("User doesn't exist!Try again");
			errorLabel.setText("User doesn't exist!Try again");
		}
	}
}
