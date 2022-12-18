package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.ClientStatus;
import common.MissionPack;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.URL;

import entities.ConnectedClient;
import entities.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.ServerConfiguration;

public class ServerController implements Initializable{

	@FXML
	private TextField DBNameField;

	@FXML
	private PasswordField DBPasswordTextField;

	@FXML
	private TextField DBUserTextField;

	@FXML
	private TextField IPTextField;

	@FXML
	private Button connectButton;

	@FXML
	private TableView<ConnectedClient> connectionTable;

	@FXML
	private TextArea consoleField;

	@FXML
	private Button disconnectButton;

	@FXML
	private TableColumn<ConnectedClient, String> hostCol;

	@FXML
	private TableColumn<ConnectedClient, String> ipCol;

	@FXML
	private TextField portTextField;

	@FXML
	private TableColumn<ConnectedClient, String> statusCol;
	
	PrintStream replaceConsole;
	
	static ObservableList<ConnectedClient> listView = FXCollections.observableArrayList();
	//private static boolean ifFirstConnector;

	public static ObservableList<ConnectedClient> getListView() {
		return listView;
	}

	public static void setListView(ObservableList<ConnectedClient> listView) {
		ServerController.listView = listView;
	}


	public void start(final Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	

	@FXML
	void clickOnConnect(MouseEvent event) {
		List<String> detailsList = new ArrayList<>();
		ServerUI.runServer(portTextField.getText());
		String ip = IPTextField.getText();
		String DBName = DBNameField.getText();
		String DBUserName = DBUserTextField.getText();
		String password = DBPasswordTextField.getText();
		if ((ip != null) && (DBName != null) && (DBUserName != null) && (password != null)) {
			detailsList.add(IPTextField.getText());
			detailsList.add(DBNameField.getText());
			detailsList.add(DBUserTextField.getText());
			detailsList.add(DBPasswordTextField.getText());
			DatabaseConnector.getDatabaseConnectorInstance().setConnectionDetailsList(detailsList);
		}
		System.out.println(DatabaseConnector.getDatabaseConnectorInstance().connect());
		connectButton.setDisable(true);
		disconnectButton.setDisable(false);
		disableDataInput(true);
	}

	@FXML
	void clickOnDisconnect(MouseEvent event) {
		ServerUI.disconnect();
		DatabaseConnector.getDatabaseConnectorInstance().clearConnectionDetailsList();
		disconnectButton.setDisable(true);
		connectButton.setDisable(false);
		disableDataInput(false);
		connectionTable.getItems().clear();
	}
	

	void disableDataInput(boolean Condition) {
		portTextField.setDisable(Condition);
		IPTextField.setDisable(Condition);
		DBNameField.setDisable(Condition);
		DBUserTextField.setDisable(Condition);
		DBPasswordTextField.setDisable(Condition);
	}

	/*public String getLocalIp() {
		String ip = null;
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ip;
	}*/


	private void setTableColumns() {
		ipCol.setCellValueFactory(new PropertyValueFactory<ConnectedClient, String>("ip"));
		hostCol.setCellValueFactory(new PropertyValueFactory<ConnectedClient, String>("host"));
		statusCol.setCellValueFactory(new PropertyValueFactory<ConnectedClient, String>("status"));
	}

	void consoleStreamIntoGUI() {
		replaceConsole = new PrintStream(new gui.Console(consoleField));
		System.setOut(replaceConsole);
		System.setErr(replaceConsole);
	}

	/*public static void SetObser(MissionPack obj) {
		@SuppressWarnings("unchecked")
		final ArrayList<String> list = (ArrayList<String>) obj.getInformation();
		final ConnectedClient client = new ConnectedClient(list.get(0), list.get(1), ClientStatus.CONNECTED);
		listView.add(client);
		if (ifFirstConnector) {
			listView.remove(0);
			ifFirstConnector = false;
		}
	}*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize the table columns about the connected clients detail
				connectionTable.setItems(ServerConfiguration.getClientList());
				// Setting up our TableView columns
				setTableColumns();
				// Change output stream into the ServerGUI Console Area
				consoleStreamIntoGUI();
				IPTextField.setText("localhost");
				portTextField.setText("5555");
				DBNameField.setText("jdbc:mysql://localhost/ekrut?serverTimezone=IST");
				DBUserTextField.setText("root");
				DBPasswordTextField.setText("Aa123456");
				disconnectButton.setDisable(true);
	}
}
