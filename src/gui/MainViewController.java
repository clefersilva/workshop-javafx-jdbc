package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.sevices.DepartamentoService;

@SuppressWarnings("hiding")
public class MainViewController<DepartamentoListController> implements Initializable {

	@FXML
	private MenuItem menuItemVendedor;

	@FXML
	private MenuItem menuItemDepartamento;

	@FXML
	private MenuItem menuItemSobre;

	@FXML
	public void onMenuItemVendedorAction() {
		System.out.println("Item do menu Vendedor");
	}

	@FXML
	public void onMenuItemDepartamentoAction() {
		loadView2("/gui/Departamento.fxml");
	}

	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/sobre.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized void loadView(String absoluteNome) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteNome));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		} 
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a pagina", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteNome) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteNome));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartamentoListController controller = loader.getController();
			controller.setDapartamentoService(new DepartamentoService());
			controller.updateTableView();
		} 
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a pagina", e.getMessage(), AlertType.ERROR);
		}
	}
}
