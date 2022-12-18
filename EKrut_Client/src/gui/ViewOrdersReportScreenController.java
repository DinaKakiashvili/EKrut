package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import common.Facility;
import common.MissionPack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewOrdersReportScreenController implements EventHandler<WindowEvent> {

	private static HashMap<Facility, Integer> information;
	private static String Date;
	private static String region;

	@FXML
	private Button backBtn;

	@FXML
	private Label infoLabel;

	@FXML
	private PieChart ordersPie;

	@FXML
	private Label ordersReportLabl;

	@FXML
	void clickOnBackBtn(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		ChooseReportScreenController chooseReportScreen = new ChooseReportScreenController();
		chooseReportScreen.start(primaryStage);
	}

	public static HashMap<Facility, Integer> getInformation() {
		return information;
	}

	@SuppressWarnings("unchecked")
	public static void setInformation(Object information) {
		ViewOrdersReportScreenController.information = (HashMap<Facility, Integer>) information;
	}

	public static String getDate() {
		return Date;
	}

	public static void setDate(String date) {
		Date = date;
	}

	public static String getRegion() {
		return region;
	}

	public static void setRegion(String region) {
		ViewOrdersReportScreenController.region = region;
	}

	public void initialize() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (Map.Entry<Facility, Integer> currentSet : information.entrySet()) {
			Facility ekrutFacility = currentSet.getKey();
			Integer ekrutNumOfOrder = currentSet.getValue();
			pieChartData.add(new PieChart.Data(ekrutFacility.toString(), ekrutNumOfOrder));
		}

		ordersPie.setData(pieChartData);
		infoLabel.setText(region + ", " + Date);
	}

	public void start(Stage primaryStage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/ViewOrdersReportScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("E-Krut View Reports");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub

	}

}
