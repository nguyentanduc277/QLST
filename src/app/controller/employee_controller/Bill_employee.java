package app.controller.employee_controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.dao.connectDB;
import app.model.Bill;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Bill_employee implements Initializable{

	
    @FXML
    private Label text_code;

    @FXML
    private Label text_name;

    @FXML
    private TextField text_amount;

    @FXML
    private Label text_type;

    @FXML
    private Label text_price;

    @FXML
    private Label text_total;

    @FXML
    private TableColumn<Bill,String> col_name;

    @FXML
    private TableColumn<Bill,String> col_barcode;
    @FXML
    private TableColumn<Bill,String> col_type;

    @FXML
    private TableColumn<Bill,String> col_price;

    @FXML
    private TableView<Bill> table_bill;
    
    @FXML
    private TextArea bill;
    
    @FXML
    private TextField search_bill;
    

    @FXML
    private Label ltotal;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Bill> listM;
    ObservableList<Bill> dataList;

    
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable_bill();
    search_user_bill();
    num=0;

    // Code Source in description
    } 
    
    
    
    @FXML
    void pay(ActionEvent event) {        

    	int total = 0;
        int x = Integer.parseInt(text_price.getText());
        int y = Integer.parseInt(text_amount.getText());
        total=(x*y);
        ltotal.setText(total+"");
    	
    	
    	
        String s=bill.getText();
        
        bill.setText(s+"Name Of Product : "+text_name.getText()+"\n"+"Price Of Product : "+text_price.getText()+"\n"
        +"Discount Of Product : "+text_amount.getText()+"\n"+"Total :"+total+"\n"+"\n--------------------------------------\n"
        );
        
    }

    int num =0;
    public void Print() {
        
        try {
            num++;
             PrintWriter f = new PrintWriter("bill "+String.valueOf(num)+".txt");
             f.println(bill.getText());
            f.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bill_employee.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
                 
        bill.setText("");
  
  }
    
    
    public void UpdateTable_bill(){

    	
    	col_name.setCellValueFactory(new PropertyValueFactory<Bill,String>("name"));
    	col_type.setCellValueFactory(new PropertyValueFactory<Bill,String>("brand"));
    	col_price.setCellValueFactory(new PropertyValueFactory<Bill,String>("price"));
    	col_barcode.setCellValueFactory(new PropertyValueFactory<Bill,String>("code"));
    	


        listM = connectDB.getDatausers_bill();
        table_bill.setItems(listM);
    }
    
    
    @FXML
    void getSelected_bill(MouseEvent event) {
        index = table_bill.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        
        text_name.setText(col_name.getCellData(index).toString());
        text_code.setText(col_barcode.getCellData(index).toString());
        text_price.setText(col_price.getCellData(index).toString());
 
    }
    
    

    @FXML
    void search_user_bill(){
    	col_name.setCellValueFactory(new PropertyValueFactory<Bill,String>("name"));
    	col_price.setCellValueFactory(new PropertyValueFactory<Bill,String>("price"));
    	col_barcode.setCellValueFactory(new PropertyValueFactory<Bill,String>("code"));
    	col_type.setCellValueFactory(new PropertyValueFactory<Bill,String>("brand"));

	           dataList = connectDB.getDatausers_bill();
	           table_bill.setItems(dataList);
	           FilteredList<Bill> filteredData = new FilteredList<>(dataList, b -> true);  
	           search_bill.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	       if (newValue == null || newValue.isEmpty()) {
	        return true;
	       }    
	       String lowerCaseFilter = newValue.toLowerCase();
	       
	       if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	        return true; // Filter matches name
	       } else if (person.getPrice().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	        return true; // Filter matches email
	       }else if (person.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	        return true; // Filter matches phone
	       }
//	       else if (String.valueOf(person.getBusiness()).indexOf(lowerCaseFilter)!=-1)
//	            return true;// Filter matches username
	                                   
	            else  
	             return false; // Does not match.
	      });
	     });  
	     SortedList<Bill> sortedData = new SortedList<>(filteredData);  
	     sortedData.comparatorProperty().bind(table_bill.comparatorProperty());  
	     table_bill.setItems(sortedData);      
	       
    }
    
    
	
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
