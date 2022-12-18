package gui;

import java.io.IOException;

import common.ProductInStock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ShoppingWindowController implements EventHandler<WindowEvent>{
	
	private static ProductInStock[] products;
	private static String facility;

	@FXML
    private Button AddProducts;

	@FXML
    private Button ShoppingCart;

    @FXML
    private Button backBtn;

    @FXML
    private Spinner<Integer> BisliAdd;

    @FXML
    private Spinner<Integer> bambaAdd;

    @FXML
    private Spinner<Integer> buenoAdd;

    @FXML
    private Spinner<Integer> chocolateAdd;

    @FXML
    private Spinner<Integer> colaAdd;

    @FXML
    private Spinner<Integer> kifkefAdd;

    @FXML
    private Spinner<Integer> mnmAdd;

    @FXML
    private Spinner<Integer> snydersAdd;

    @FXML
    private Spinner<Integer> spriteAdd;

    @FXML
    private Spinner<Integer> teaAdd;

    @FXML
    private Spinner<Integer> teamiAdd;

    @FXML
    private Spinner<Integer> waterAdd;
    
    @FXML
 	public void initialize() {
    	if(facility.equals("WAREHOUSE"))
    		initForRemote();
    	else
    		initForLocal();
 	}

    @FXML
    void Back(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	final Stage primaryStage = new Stage();
    	ConfigurationScreenController configPage = new ConfigurationScreenController();
    	configPage.start(primaryStage);
    }
    

    public void start(Stage primaryStage) throws IOException {
    	System.out.println("start shopping");
    	Parent root = FXMLLoader.load(getClass().getResource("/gui/ShoppingWindow.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Shopping window");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(this);
	}
    
    public static ProductInStock[] getProducts() {
		return products;
	}

	public static void setProducts(ProductInStock[] products) {
		ShoppingWindowController.products = products;
	}
	
    public static String getFacility() {
		return facility;
	}

	public static void setFacility(String facility) {
		ShoppingWindowController.facility = facility;
	}

	@Override
	public void handle(WindowEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private void initForLocal()
	{
		SpinnerValueFactory<Integer> bisliValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[0].getAmount(),0,1);
    	bisliValueFactory.setValue(0);
    	BisliAdd.setValueFactory(bisliValueFactory);
    	
    	SpinnerValueFactory<Integer> bambaValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[6].getAmount(),0,1);
    	bambaValueFactory.setValue(0);
    	bambaAdd.setValueFactory(bambaValueFactory);
    	
    	SpinnerValueFactory<Integer> buenoValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[9].getAmount(),0,1);
    	buenoValueFactory.setValue(0);
    	buenoAdd.setValueFactory(buenoValueFactory);
    	
    	SpinnerValueFactory<Integer> chocolateValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[1].getAmount(),0,1);
    	chocolateValueFactory.setValue(0);
    	chocolateAdd.setValueFactory(chocolateValueFactory);
    	
    	SpinnerValueFactory<Integer> colaValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[2].getAmount(),0,1);
    	colaValueFactory.setValue(0);
    	colaAdd.setValueFactory(colaValueFactory);
    	
    	SpinnerValueFactory<Integer> kifkefValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[8].getAmount(),0,1);
    	kifkefValueFactory.setValue(0);
    	kifkefAdd.setValueFactory(kifkefValueFactory);
    	
    	SpinnerValueFactory<Integer> mnmfValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[10].getAmount(),0,1);
    	mnmfValueFactory.setValue(0);
    	mnmAdd.setValueFactory(mnmfValueFactory);
    	
    	SpinnerValueFactory<Integer> snydersValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[3].getAmount(),0,1);
    	snydersValueFactory.setValue(0);
    	snydersAdd.setValueFactory(snydersValueFactory);
    	
    	SpinnerValueFactory<Integer> spriteValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[4].getAmount(),0,1);
    	spriteValueFactory.setValue(0);
    	spriteAdd.setValueFactory(spriteValueFactory);
    	
    	SpinnerValueFactory<Integer> teaValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[7].getAmount(),0,1);
    	teaValueFactory.setValue(0);
    	teaAdd.setValueFactory(teaValueFactory);
    	
    	SpinnerValueFactory<Integer> teamiValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[11].getAmount(),0,1);
    	teamiValueFactory.setValue(0);
    	teamiAdd.setValueFactory(teamiValueFactory);
    	
    	SpinnerValueFactory<Integer> waterValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,products[5].getAmount(),0,1);
    	waterValueFactory.setValue(0);
    	waterAdd.setValueFactory(waterValueFactory);
	}
	
	private void initForRemote()
	{
		SpinnerValueFactory<Integer> bisliValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	bisliValueFactory.setValue(0);
    	BisliAdd.setValueFactory(bisliValueFactory);
    	
    	SpinnerValueFactory<Integer> bambaValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	bambaValueFactory.setValue(0);
    	bambaAdd.setValueFactory(bambaValueFactory);
    	
    	SpinnerValueFactory<Integer> buenoValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	buenoValueFactory.setValue(0);
    	buenoAdd.setValueFactory(buenoValueFactory);
    	
    	SpinnerValueFactory<Integer> chocolateValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	chocolateValueFactory.setValue(0);
    	chocolateAdd.setValueFactory(chocolateValueFactory);
    	
    	SpinnerValueFactory<Integer> colaValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	colaValueFactory.setValue(0);
    	colaAdd.setValueFactory(colaValueFactory);
    	
    	SpinnerValueFactory<Integer> kifkefValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	kifkefValueFactory.setValue(0);
    	kifkefAdd.setValueFactory(kifkefValueFactory);
    	
    	SpinnerValueFactory<Integer> mnmfValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	mnmfValueFactory.setValue(0);
    	mnmAdd.setValueFactory(mnmfValueFactory);
    	
    	SpinnerValueFactory<Integer> snydersValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	snydersValueFactory.setValue(0);
    	snydersAdd.setValueFactory(snydersValueFactory);
    	
    	SpinnerValueFactory<Integer> spriteValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	spriteValueFactory.setValue(0);
    	spriteAdd.setValueFactory(spriteValueFactory);
    	
    	SpinnerValueFactory<Integer> teaValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	teaValueFactory.setValue(0);
    	teaAdd.setValueFactory(teaValueFactory);
    	
    	SpinnerValueFactory<Integer> teamiValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	teamiValueFactory.setValue(0);
    	teamiAdd.setValueFactory(teamiValueFactory);
    	
    	SpinnerValueFactory<Integer> waterValueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0,1);
    	waterValueFactory.setValue(0);
    	waterAdd.setValueFactory(waterValueFactory);
	}

}
