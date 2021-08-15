package app.controller.homepage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import app.dao.connectDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Home_Manage {
	
	Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    @FXML
    private Label id;
	
	@FXML
    private Label user;

    @FXML
    private Label title;

	@FXML
	void home(MouseEvent event) {

	}
	
	
	@FXML
	void exit(MouseEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../ui/homepage/Login.fxml"));
			Parent parent;

			parent = loader.load();

			Scene scene = new Scene(parent);
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
    void toInformation(MouseEvent event) throws IOException, SQLException {	
//		Connection conn=connectDB.ConnectDb();
//		pst = conn.prepareStatement("select * from employee where employee_name = ? and employee_position=?");	
//		pst.setString(1, a2);
//		pst.setString(2, a3);
//		ResultSet rs = pst.executeQuery();
//					
//		
//		if (rs.next()) {
//				System.out.println("kiem tra thanh cong");
//				FXMLLoader loader=new FXMLLoader();
//				loader.setLocation(getClass().getResource("../view/Info_Employee.fxml"));
//				id=rs.getString("employee_id");
//				name=rs.getString("employee_name");
//				position=rs.getString("employee_position");
//				rank_1=rs.getString("employee_rank");
//				email=rs.getString("employee_email");
//				phone=rs.getString("employee_phone");
//				username=rs.getString("employee_username");
//				Parent parent=loader.load();	
//				Info_Employee info=loader.getController();
//				info.getInfo(name, position, id, rank_1,email,phone,username);
//				mainPane.setCenter(parent);
//			}else{
//        		JOptionPane.showMessageDialog(null, "Ko do dc.");	
//        	}
//			conn.close();
	}
	
	

	@FXML
	void logout(MouseEvent event) throws SQLException, IOException {
		Connection conn=connectDB.ConnectDb();
		pst = conn.prepareStatement("update employee set emp_status=0 where emp_id='"+a1+"' ");
		pst.execute();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../ui/homepage/Login.fxml"));
		Parent parent;
		parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
	}
	
	private static String a1,a2,a3;
	public void getId(String id1,String user1,String title1) {
		id.setText(id1);
		user.setText(user1);
		title.setText(title1);
		a1=id.getText();
		a2=user.getText();
		a3=title.getText();
		System.out.println(a1+" / "+a2+" / "+a3);
	}

}
