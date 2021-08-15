package app.controller.homepage;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

import app.dao.connectDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Info_Employee {

	EncryptorAES encryptorAES = new EncryptorAES();
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextField txtPosition;

	@FXML
	private TextField txtUsename;

	@FXML
	private PasswordField txtOldPass;

	@FXML
	private PasswordField txtNewPass;

	@FXML
	private PasswordField txtCofirmPass;

	@FXML
	private DatePicker txtDate;

	@FXML
	private TextField txtRank;
	
	@FXML
    private TextField txtId;
	private static String oldPass;
	@FXML
	void btnRegister(MouseEvent event){
		try {

			conn = connectDB.ConnectDb();
                
            String value1 = txtId.getText();
            String value2 = txtName.getText();
            String value3 = txtEmail.getText();
            String value4 = txtPhone.getText();
            String value5 = txtPosition.getText(); ;
            String value9 = txtDate.getValue().toString();
            
    					
				String sql = "update employee set employee_id= '"+value1+"',employee_name= '"+value2+"',employee_email= '"+
	                    value3+"',employee_phone= '"+value4+"',employee_position= '"+value5+"',employee_dateofbirth= '"+value9+"' where employee_id='"+value1+"' ";
	            pst= conn.prepareStatement(sql);
	            pst.execute();	
	            JOptionPane.showMessageDialog(null, "Updated Successful");
			conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "updated Fail. Insert Yourt's Birthdate");          
        }
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
	
	@FXML
    void btnChangePass(MouseEvent event) throws SQLException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		conn = connectDB.ConnectDb();
        
        String new_pass = txtNewPass.getText();
		String enBase64 = encode(new_pass);
		String key = "65 12 12 12 12 12 12 12 12 12 12 12 12 12 12 11";
		String encryptedString = null;
		encryptedString = encryptorAES.encrypt(enBase64, key);

		String confirm_pass = txtCofirmPass.getText();
		String enBase64_1 = encode(confirm_pass);
		String key1 = "65 12 12 12 12 12 12 12 12 12 12 12 12 12 12 11";
		String encryptedString1 = null;
		encryptedString1 = encryptorAES.encrypt(enBase64_1, key1);
		
		String old_pass = txtOldPass.getText();
		String enBase64_2 = encode(old_pass);
		String key2 = "65 12 12 12 12 12 12 12 12 12 12 12 12 12 12 11";
		String encryptedString2 = null;
		encryptedString2 = encryptorAES.encrypt(enBase64_2, key2);
		
		String value1 = txtId.getText();
        String value7 = encryptedString;
        pst = conn.prepareStatement("select * from employee where employee_password=?");
		pst.setString(1, encryptedString2);
		ResultSet rs = pst.executeQuery();
		if (rs.next() && encryptedString.equals(encryptedString1)==true) {	
			String sql="update employee set employee_password= '"+value7+"' where employee_id='"+value1+"' ";
			 pst= conn.prepareStatement(sql);
	            pst.execute();	
	            JOptionPane.showMessageDialog(null, "Change Password Successful");
			conn.close();
		}else {
			JOptionPane.showMessageDialog(null, "Change Password Fail.");
		}
    }
	/*--------------------------------------------------------------------------*/
	 public void getinfo() throws SQLException, ParseException {
		 pst = conn.prepareStatement("select * from employee where employee_id=?");
		 pst.setString(1, a4);
		 ResultSet rs = pst.executeQuery();
		 if (rs.next()) {
			 String date1=rs.getString("employee_dateofbirth");
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				 Date date = formatter.parse(date1);
		            System.out.println(date);
		 }
	 }
	
	private static String a2,a3,a4,a5,a6,a7,a8;
	public void getInfo(String name2, String position, String id,String rank,String email,String phone,String username) {
		// TODO Auto-generated method stub
		txtName.setText(name2);    	
    	txtPosition.setText(position);
    	txtId.setText(id);
    	txtRank.setText(rank);
    	txtUsename.setText(username);    	
    	txtEmail.setText(email);
    	txtPhone.setText(phone);
    	a2=txtName.getText();
    	a3=txtPosition.getText();
    	a4=txtId.getText();
    	a5=txtRank.getText();
    	a6=txtUsename.getText();
    	a7=txtEmail.getText();
    	a8=txtPhone.getText();
    	System.out.println("-----Emloyee----------"+a2+" / "+a3+" / "+a4+" / "+a5+" / "+a6+" / "+a7+" / "+a8);
    	
	}


	private Object getCellData(String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
