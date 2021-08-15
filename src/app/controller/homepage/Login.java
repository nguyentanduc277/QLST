package app.controller.homepage;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
	
	EncryptorAES encryptorAES = new EncryptorAES();
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
    private static String id,title_id,user,status;
    @FXML
    void btn_login(ActionEvent event) throws SQLException, IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
    	
    	String pass=getPassword();
		String enBase64=encode(pass);
        String key = "65 12 12 12 12 12 12 12 12 12 12 12 12 12 12 11";
        String encryptedString = null;           
            encryptedString = encryptorAES.encrypt(enBase64,key);  
    	
    	String username= txt_username.getText();
//    	String pass1=getPassword();
    	String pass1=encryptedString;
		Connection conn=connectDB.ConnectDb();
		pst = conn.prepareStatement("select * from employee where emp_user = ? and emp_pass=?");
		pst.setString(1, username);
		pst.setString(2, pass1);
		ResultSet rs = pst.executeQuery();					
		if (rs.next()) {
				System.out.println("dang nhap thanh cong");
				id=rs.getString("emp_id");	
				title_id=rs.getString("title_id");
				user=rs.getString("emp_name");
				status=rs.getString("emp_status");
				if(status.equals("1")) {
					JOptionPane.showMessageDialog(null, "Tai Khoan Dang Dang Nhap Tren May Khac.");	
				}else {
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
				}							
			}else {
        		JOptionPane.showMessageDialog(null, "Check username or password.");	
        	}
			conn.close();
	}
    
    @FXML
    void logout(MouseEvent event) {
    	System.exit(0);
    }
    public void loadHome(String ui) {
    	
    }
	/*--------------------------------------------------------------------------*/
	public static String encode(String str) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] encoded = encoder.encode(str.getBytes());
		return new String(encoded);

	}

	public static String decode(String str) {
		Base64.Decoder decode = Base64.getDecoder();
		byte[] decoded = decode.decode(str);
		return new String(decoded);

	}
}
