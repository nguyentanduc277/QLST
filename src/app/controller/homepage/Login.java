package app.controller.homepage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import app.dao.connectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Login {
	
//	EncryptorAES encryptorAES = new EncryptorAES();
	Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
	
    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_passhiden;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private CheckBox btn_showpass;
    
    @FXML
    void show_pass(ActionEvent event) {
    	if(btn_showpass.isSelected()) {
    		txt_passhiden.setText(txt_pass.getText());
    		txt_passhiden.setVisible(true);
    		txt_pass.setVisible(false);
    		return;
    	}
    	txt_pass.setText(txt_passhiden.getText());
    	txt_pass.setVisible(true);
    	txt_passhiden.setVisible(false);
    }
    private String getPassword(){
        if(txt_passhiden.isVisible()){
            return txt_passhiden.getText();
        } else {
            return txt_pass.getText();
        }
    }
    private static String id,title_id,user;
    @FXML
    void btn_login(ActionEvent event) throws SQLException, IOException {
    	
//    	String pass=getPassword();
//		String enBase64=encode(pass);
//        String key = "65 12 12 12 12 12 12 12 12 12 12 12 12 12 12 11";
//        String encryptedString = null;           
//            encryptedString = encryptorAES.encrypt(enBase64,key);  
    	
    	String username= txt_username.getText();
    	String pass=getPassword();
		Connection conn=connectDB.ConnectDb();
		pst = conn.prepareStatement("select * from employee where emp_user = ? and emp_pass=? and emp_status=0");
		pst.setString(1, username);
		pst.setString(2, pass);
		ResultSet rs = pst.executeQuery();
					
		if (rs.next()) {
				System.out.println("dang nhap thanh cong");
				id=rs.getString("emp_id");	
				title_id=rs.getString("title_id");
				user=rs.getString("emp_name");
				if (title_id.equals("1")) {
					Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource("../../ui/homepage/Home_Manage.fxml"));								
					Parent parent=loader.load();
					String sql = "update employee set emp_status=1 where emp_id='"+id+"' ";
		            pst= conn.prepareStatement(sql);
		            pst.execute();
		            Home_Manage home=loader.getController();
		            home.getId(id,user,title_id);
					Scene scene=new Scene(parent);				
					stage.setScene(scene);
				}else if (title_id.equals("2")) {
					Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource("../../ui/homepage/Home_Employee.fxml"));							
					Parent parent=loader.load();
					String sql = "update employee set emp_status=1 where emp_id='"+id+"' ";
		            pst= conn.prepareStatement(sql);
		            pst.execute();
		            Home_Employee home=loader.getController();
		            home.getId(id,user,title_id);
					Scene scene=new Scene(parent);				
					stage.setScene(scene);
				}
				
			}else if(username==""||pass==""){
        		JOptionPane.showMessageDialog(null, "Check username or password.");	
        	}else {
        		JOptionPane.showMessageDialog(null, "Tai Khoan Dang Dang Nhap Tren May Khac.");	
        	}
			conn.close();
	}
    
    @FXML
    void logout(MouseEvent event) {
    	System.exit(0);
    }
    public void loadHome(String ui) {
    	
    }
}
