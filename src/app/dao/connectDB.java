package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.model.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class connectDB {
	Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/qlst","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            System.out.println("ket noi database thanh cong");
            return conn;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	Connection conn=connectDB.ConnectDb();
    	
    }
    public static ObservableList<Bill> getDatausers_bill() {
        Connection conn = ConnectDb();
        ObservableList<Bill> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select product.pro_name,brand.brand_name ,product.pro_sale_price,product.barcode from product INNER JOIN brand ON product.brand_id = brand.brand_id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Bill(rs.getString("pro_name"),rs.getString("brand_name"),rs.getString("pro_sale_price"),rs.getString("barcode")));       
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
	}
    
    
}
