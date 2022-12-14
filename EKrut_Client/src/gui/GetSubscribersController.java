package gui;

import entities.*;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import client.ClientMissionHandler;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class GetSubscribersController implements EventHandler<WindowEvent> {
	@FXML
	private Button backBtn;

	@FXML
	private TableColumn<Subscriber, String> creditCardColTbl;

	@FXML
	private Button editSubscriberBtn;


    @FXML
    private TableColumn<Subscriber, String> emailColTbl;

	@FXML
	private TableColumn<Subscriber, String> firstNameColTbl;

	@FXML
	private Button getSubscribersBtn;

	@FXML
	private TableColumn<Subscriber, String> idColTbl;

	@FXML
	private TableColumn<Subscriber, String> lastNameColTbl;

	@FXML
	private TextField lblEditCreditCard;

	@FXML
	private TextField lblEditSubscriber;

	@FXML
	private TextField lblInsertId;

	@FXML
	private TableColumn<Subscriber, String> phoneNumberColTbl;

	@FXML
	private Label statusLabel;

	@FXML
	private TableColumn<Subscriber, String> subscriberNumberColTbl;

	@FXML
	private TableView<Subscriber> table;
	ObservableList<Subscriber> subscribers = FXCollections.observableArrayList();

	public void start(final Stage primaryStage) throws Exception {
		final Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("/gui/GetSubscribers.fxml"));
		final Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(this);
	}

	public void initialize() {
		setColumnsInTable();
		// This method is requesting data from the Server
		subscribers.clear();
		// Setting the Data to be displayed in the TableView
		table.setItems(subscribers);
		table.autosize();
		table.setEditable(true);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setColumnsInTable() {
		idColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("id"));
		firstNameColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("firstName"));
		lastNameColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("lastName"));
		phoneNumberColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("phoneNumber"));
		emailColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("emailAddress"));
		creditCardColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("creditCardNumber"));
		subscriberNumberColTbl.setCellValueFactory((Callback) new PropertyValueFactory<Subscriber, String>("subscriberNumber"));
		
	}

	@FXML
	void Back(final ActionEvent event) throws Exception {
		ClientMissionHandler.DISCONNECT_FROM_SERVER();
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		ClientOpeningScreenController openScreen = new ClientOpeningScreenController();
		openScreen.start(primaryStage);
	}

	@FXML
	void GetSubscribers(final ActionEvent event) {
		ClientMissionHandler.GET_SUBSCRIBERS(this.subscribers, this.table, this.statusLabel);
	}

	@FXML
	void EditSubscriber(final ActionEvent event) {
		ClientMissionHandler.EDIT_SUBSCRIBERS(this.statusLabel, this.lblEditCreditCard, this.lblEditSubscriber,
				this.lblInsertId);
	}

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}

}