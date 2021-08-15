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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Home_Manage {
	
	Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    @FXML
    private BorderPane mainPane;
    @FXML
    private Label id;
	
	@FXML
    private Label user;

    @FXML
    private Label title;
    
    @FXML
    void home(MouseEvent event) throws SQLException, IOException {
    	Connection conn=connectDB.ConnectDb();
		pst = conn.prepareStatement("select * from employee where emp_id=?");
		pst.setString(1,a1);
		ResultSet rs = pst.executeQuery();
					
		
		if (rs.next()) {
				System.out.println("dang nhap thanh cong");
				Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation(getClass().getResource("../../ui/homepage/Home_Manage.fxml"));								
				Parent parent=loader.load();
	            Home_Manage home=loader.getController();
	            home.getId(a1,a2,a3);
				Scene scene=new Scene(parent);				
				stage.setScene(scene);			
			}else{
        		JOptionPane.showMessageDialog(null, "Usename or Password Blank.");	
        	}		
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
	
	
	private static String emp_id, name,phone,email,username,title_name,date;
	@FXML
    void toInformation(MouseEvent event) throws IOException, SQLException {
		
		Connection conn=connectDB.ConnectDb();
		pst = conn.prepareStatement("select * from employee where emp_id=?");	
		pst.setString(1, a1);
		ResultSet rs = pst.executeQuery();
					
		
		if (rs.next()) {
				System.out.println("kiem tra thanh cong");
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation(getClass().getResource("../../ui/homepage/Info_Employee.fxml"));
				Parent parent=loader.load();
				emp_id=rs.getString("emp_id");
				name=rs.getString("emp_name");
				phone=rs.getString("emp_phone");
				email=rs.getString("emp_email");
				username=rs.getString("emp_user");
				date=rs.getString("emp_birthday");
				int title_id=rs.getInt("title_id");
				pst = conn.prepareStatement("select * from title where title_id=?");	
				pst.setLong(1, title_id);
				ResultSet rs1 = pst.executeQuery();
				if (rs1.next()) {
					title_name=rs1.getString("title_name");
					System.out.println(title_name);
				}
				Info_Employee info=loader.getController();
				info.getInfo(emp_id, name, title_name, phone, email, username,date);
				mainPane.setCenter(parent);
			}else{
        		JOptionPane.showMessageDialog(null, "Ko do dc.");	
        	}
			conn.close();
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
