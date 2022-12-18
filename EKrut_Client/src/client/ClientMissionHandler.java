package client;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.List;
import common.*;
import entities.Subscriber;
import entities.Customer;
import gui.ClientLoginScreenController;
import gui.ClientUI;
import gui.GetSubscribersController;
import gui.MainCustomerPageController;
import gui.MainScreenManagerController;
import gui.ServiceRepresentativeScreen;
import gui.ViewOrdersReportScreenController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClientMissionHandler {

	public static void CONNECT_TO_SERVER(final MouseEvent event, final String ip, final String port) throws Exception {
		ClientUI.chat = new ClientController(ip, Integer.parseInt(port)); // create the static object of chat in
																			// clientUI
		if (ClientUI.chat != null) {
			MissionPack obj = new MissionPack(Mission.SEND_CONNECTION_DETAILS, null, null); // create a mission pack
																							// that sends the connection
																							// details to the server
			final ArrayList<String> details = new ArrayList<String>();
			details.add(InetAddress.getLocalHost().getHostAddress());
			details.add(InetAddress.getLocalHost().getHostName());
			obj.setInformation(details); // the information is an arraylist that contains the connection details
			// System.out.println("in client");
			ClientUI.chat.accept(obj);
			/*
			 * send the object to the server, from now on it will be taken care of in the
			 * server
			 * (->handleMessageFromClient->handleMessages->parsingToData->..QueryExecutor)
			 * an updated object of MissionPack is now in obj and within it the response and
			 * needed information
			 */
			obj = ClientUI.chat.getResponseFromServer();
			if (obj.getResponse().equals(Response.UPDATE_CONNECTION_SUCCESS)) {
				((Node) event.getSource()).getScene().getWindow().hide();
				final Stage primaryStage = new Stage();
				ClientLoginScreenController subController = new ClientLoginScreenController();
				subController.start(primaryStage); // connection successful->goto the next window
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
		// System.out.println("hooooooooooooooooo");
		String[] data = { username, password };
		MissionPack obj = new MissionPack(Mission.IDENTIFY_USER, null, data); // create mission to login and send login
																				// data
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		String firstName = ((String[]) obj.getInformation())[4];
		String phone = ((String[]) obj.getInformation())[3];
		String role = ((String[]) obj.getInformation())[2];

		if (obj.getResponse().equals(Response.LOGIN_SUCCEED)) {
			((Node) event.getSource()).getScene().getWindow().hide();
			final Stage primaryStage = new Stage();
			if (role.equals("customer")) {
				MainCustomerPageController.setFirstName(firstName);
				MainCustomerPageController.setPhone(phone);
				MainCustomerPageController.setRole(role);
				MainCustomerPageController subController = new MainCustomerPageController();
				subController.start(primaryStage);
			} else if (role.equals("ServiceRepresentative")) {
				ServiceRepresentativeScreen ServiceRepresentative = new ServiceRepresentativeScreen();
				ServiceRepresentative.start(primaryStage);
			} else if (role.equals("RegionalManager")) {
				String region = ((String[]) obj.getInformation())[5];
				MainScreenManagerController.setFirstName(firstName);
				MainScreenManagerController.setPhone(phone);
				MainScreenManagerController.setRole(role);
				MainScreenManagerController.setRegion(region);
				MainScreenManagerController RegionalManager = new MainScreenManagerController();
				RegionalManager.start(primaryStage);
			} else if (role.equals("CEO")) {
				String storeName = ((String[]) obj.getInformation())[5];
			}

		} else {
			// System.out.println("User doesn't exist!Try again");
			errorLabel.setText("User doesn't exist!Try again");
		}
	}

	public static void AddingCustomerNew(final TextField IDText, final TextField FirstNameText,
			final TextField LastNameText, final TextField PhoneNumberText, final TextField EmailAddressText,
			final TextField CreditCardNumberText, Label status) {
		final Customer newone = new Customer(IDText.getText(), FirstNameText.getText(), LastNameText.getText(),
				PhoneNumberText.getText(), EmailAddressText.getText(), CreditCardNumberText.getText());

		MissionPack obj = new MissionPack(Mission.ADD_CUSTOMER_DATA, null, newone);
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse().equals(Response.ADD_CUSTOMER_DATA_SUCCESS)) {
			status.setText("Awaiting regional manager approval");
			System.out.println(
					"the The user has been successfully added to the system as a customer and waiting for the approval of the regional manager");
		} else {
			status.setText("The Attempt failed");
		}

	}

	public static void fromCustomerToSubscriber(final TextField ID, Label status, Label status2) {
		String idToSearch = (String) ID.getText();
		MissionPack obj = new MissionPack(Mission.FROM_CUSTOMER_TO_SUBSCRIBER, null, idToSearch);
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse().equals(Response.FROM_CUSTOMER_TO_SUBSCRIBER_SUCCESS)) {
			status.setText("Changed to subscription successfully");
			status2.setText("Congratulations! The subscriber number is " + idToSearch + idToSearch);
			System.out.println("the The user has been successfully added to the system as a subscriber ");
		} else {
			status.setText("The Attempt failed");
		}

	}

	public static void GET_ORDERS_REPORT(MouseEvent event, Label errorLabel, ArrayList<String> reportDetails) {
		MissionPack obj = new MissionPack(Mission.GET_MONTHLY_ORDERS_REPORT, null, reportDetails);
		ClientUI.chat.accept(obj);
		ViewOrdersReportScreenController.setDate(reportDetails.get(0) + " / " + reportDetails.get(1));
		ViewOrdersReportScreenController.setRegion(reportDetails.get(2));
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse() == Response.GET_MONTHLY_ORDERS_REPORT_FAILD) {
			errorLabel.setVisible(true);
			errorLabel.setText("No such report");
		} else {
			((Node) event.getSource()).getScene().getWindow().hide();
			final Stage primaryStage = new Stage();
			ViewOrdersReportScreenController.setInformation(obj.getInformation());
			ViewOrdersReportScreenController showreportScreen = new ViewOrdersReportScreenController();
			showreportScreen.start(primaryStage);
		}
	}

	public static void GET_STOCK_REPORT(MouseEvent event, Label errorLabel, ArrayList<String> reportDetails) {
		MissionPack obj = new MissionPack(Mission.GET_MONTHLY_STOCK_REPORT, null, reportDetails);
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse() == Response.GET_MONTHLY_STOCK_REPORT_FAILD) {
			errorLabel.setVisible(true);
			errorLabel.setText("No such report");
		} else {
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			// scene.changeScreen(new Stage(), "/GuiClientScreens/MonthlyOrdersReport.fxml",
			// true);
		}
	}

	public static void GET_CUSTOMER_REPORT(MouseEvent event, Label errorLabel, ArrayList<String> reportDetails) {
		MissionPack obj = new MissionPack(Mission.GET_MONTHLY_CUSTOMER_REPORT, null, reportDetails);
		ClientUI.chat.accept(obj);
		obj = ClientUI.chat.getResponseFromServer();
		if (obj.getResponse() == Response.GET_MONTHLY_CUSTOMER_REPORT_FAILD) {
			errorLabel.setVisible(true);
			errorLabel.setText("No such report");
		} else {
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			// scene.changeScreen(new Stage(), "/GuiClientScreens/MonthlyOrdersReport.fxml",
			// true);
		}
	}

}
