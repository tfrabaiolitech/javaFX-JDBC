package gui;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
    @FXML
	public void menuItemSellerAction() {
		System.out.println("menuItemSellerAction");
	}
	@FXML
	public void menuItemDepartmentAction() { // função de inicialização como parâmetro
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		}); 
	}
	
	@FXML
	public void menuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	// Função para abrir outro ecrã ->>VBOX
	//vou passar o caminho completo da About view
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			
			//Carrega outra tela
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load(); //Abrir o recurso VBOX. Recebe o absoluteName
		
		//Mostrar a view dentro da Janela principal. Temos que buscar uma referencia da cena.
		Scene mainScene = Main.getMainScene(); // referência da cena principal.
		
		                                     // O getRoot() pega o primeiro elemento da MainView. neste caso o ScrollPane
		                                     // .getContent() pega o conteúdo do ScrollPane
		                                     // fazemos o casting para o VBox
		VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // Captura o primeiro elemento da MainView. O mainVBbox
		
		// Referência para o menu
		Node mainMenu = mainVBox.getChildren().get(0); // Primeiro filho do VBox na janela principal.
		mainVBox.getChildren().clear(); // Limpa todos os childers do mainVBox
		mainVBox.getChildren().add(mainMenu); // Adiciona o mainMenu
		mainVBox.getChildren().addAll(newVBox.getChildren()); // 
		
		T controller = loader.getController(); // Executa a função que passar com argumento
		initializingAction.accept(controller);
		
		
		}
		catch(IOException e) {
			e.printStackTrace();
			//Alerts.showAlert("alert title", "Alert header",e.getMessage(), AlertType.ERROR); //Exceção caso apareça algum erro.
		}
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	

}
