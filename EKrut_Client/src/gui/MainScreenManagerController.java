package gui;

import java.io.IOException;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainScreenManagerController implements EventHandler<WindowEvent> {

	private static String firstName;
	private static String role;
	private static String region;
	private static String phone;

	@FXML
	private Button addNewCustomerBtn;

	@FXML
	private Button backBtn;

	@FXML
	private TextArea consoleUser;

	@FXML
	private Button createReportBtn;

	@FXML
	private Button viewReportsBtn;

	@FXML
	private Label welcomeLabel;

	@FXML
	void Back(ActionEvent event) {

	}

	@FXML
	void clickAddNewCustomer(MouseEvent event) {

	}

	@FXML
	void clickCreateReport(MouseEvent event) {
		
	}

	@FXML
	void clickViewReports(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		final Stage primaryStage = new Stage();
		ChooseReportScreenController reportScreen = new ChooseReportScreenController();
		reportScreen.start(primaryStage);
	}

	public static String getRegion() {
		return region;
	}

	public static void setRegion(String region) {
		MainScreenManagerController.region = region;
	}

	public String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		MainScreenManagerController.firstName = firstName;
	}

	public String getRole() {
		return role;
	}

	public static void setRole(String role) {
		MainScreenManagerController.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		MainScreenManagerController.phone = phone;
	}

	@FXML
	public void initialize() {
		welcomeLabel.setText("Welcome, " + firstName);
		consoleUser.setText("Phone number: " + phone + "\n" + "Account type: " + role);
	}

	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainScreenManager.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut Manager page");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
	}

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}
}
