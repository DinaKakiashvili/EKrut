package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import client.ClientMissionHandler;
import common.Facility;
import common.Mission;
import common.MissionPack;
import common.Response;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChooseReportScreenController implements EventHandler<WindowEvent> {

	@FXML
	private Label selectFacilityLabel;

	@FXML
	private Label selectMonthLabel;

	@FXML
	private Label selectYearLabel;

	@FXML
	private Button backBtn;

	@FXML
	private Label errorLabel;

	@FXML
	private ComboBox<String> pickFacilityComboBox;

	@FXML
	private ComboBox<String> pickMonthComboBox;

	@FXML
	private ComboBox<String> pickReportTypeComboBox;

	@FXML
	private ComboBox<String> pickYearComboBox;

	@FXML
	private Button viewBtn;

	@FXML
	void Back(ActionEvent event) {

	}

	@FXML
	void clickOnBackBtn(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		MainScreenManagerController mainManagerScreen = new MainScreenManagerController();
		mainManagerScreen.start(primaryStage);
	}

	@FXML
	void clickOnViewReport(MouseEvent event) {
		errorLabel.setVisible(false);
		if (pickMonthComboBox.getValue() == null || pickYearComboBox.getValue() == null) {
			errorLabel.setVisible(true);
			errorLabel.setText("Inorder to view report you must choose   YEAR, MONTH");
		} else if (pickReportTypeComboBox.getValue() == null) {
			errorLabel.setVisible(true);
			errorLabel.setText("You MUST choose a type of report!");
		}
		else {
			ArrayList<String> reportDetails = new ArrayList<String>(Arrays.asList(pickMonthComboBox.getValue(),
					pickYearComboBox.getValue(), MainScreenManagerController.getRegion()));
			switch (pickReportTypeComboBox.getValue()) {
			case "Orders reports":
				ClientMissionHandler.GET_ORDERS_REPORT(event, errorLabel, reportDetails);
				break;	
			
			case "Stock reports":
				ClientMissionHandler.GET_STOCK_REPORT(event, errorLabel, reportDetails);
				break;	
			
			case "Customer reports":
				ClientMissionHandler.GET_CUSTOMER_REPORT(event, errorLabel, reportDetails);
				break;
			}
		}
	}

	public void initialize() {
		ArrayList<String> months = new ArrayList<String>(
				Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));
		pickMonthComboBox.getItems().addAll(months);
		ArrayList<String> years = new ArrayList<String>(
				Arrays.asList("2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"));
		pickYearComboBox.getItems().addAll(years);
		ArrayList<String> reportsType = new ArrayList<String>(
				Arrays.asList("Orders reports", "Stock reports", "Customer reports"));
		pickReportTypeComboBox.getItems().addAll(reportsType);
		errorLabel.setVisible(false);
		pickFacilityComboBox.setValue(MainScreenManagerController.getRegion());
		pickFacilityComboBox.setDisable(true);
	}

	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ChooseReportScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Krut Reprts");
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
