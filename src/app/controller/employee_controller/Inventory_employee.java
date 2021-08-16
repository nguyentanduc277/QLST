package app.controller.employee_controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Inventory_employee {

	@FXML
void exit(MouseEvent event) {
	try {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../ui/homepage/Home_Employee.fxml"));
		Parent parent;

		parent = loader.load();

		Scene scene = new Scene(parent);
		stage.setScene(scene);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
}
