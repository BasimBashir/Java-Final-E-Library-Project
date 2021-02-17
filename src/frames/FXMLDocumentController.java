/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

/**
 *
 * @author mianx
 */
import java.io.IOException;
import util.SIGNUP;
import util.ConnectionUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static util.ConnectionUtil.connectdb;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField textEmail;
    
    @FXML
    private TextField textPassword;             
                                                        //new code after this ...
    @FXML
    private TextField TFid;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFpassword;
    @FXML
    private TextField TFretypepassword;
    @FXML
    private Button btnSignUp;
    
    Stage dialogStage2 = new Stage();
    Scene scene2;
    
    Connection connection3 = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet2 = null;
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public FXMLDocumentController() {
        connection = ConnectionUtil.connectdb(); //new connection after this...
        connection3 = SIGNUP.connectdb2();
    }
    
    public void signed(ActionEvent event) throws IOException{    
        if(event.getSource() == btnSignUp){
            String pass= null;
            String pass2= null;
            String user = "";
            String user2 = "";
            pass = TFpassword.getText().toString();
            pass2 = TFretypepassword.getText().toString();
            user = TFemail.getText();
            do{                       //check if password and retype passwords are equal  and email is not empty
                if(pass2 == null ? pass == null : pass2.equals(pass)){   
                        
                    if(user == null ? user2 == null : user.equals(user2)){
                        infoBox("Email Field can't be empty", null, "Prompt!");
                    }else{
                        String username = TFemail.getText().toString();
                        String sql = "SELECT * FROM data WHERE email = ?";
                            try{
                                preparedStatement = connection.prepareStatement(sql);
                                preparedStatement.setString(1, username);
                                resultSet = preparedStatement.executeQuery();
                                if(!resultSet.next()){
                                Node node = (Node)event.getSource();
                                dialogStage = (Stage) node.getScene().getWindow();
                                dialogStage.close();
                                insertRecord();
                                scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")));
                                dialogStage.setScene(scene);
                                dialogStage.show();
                                
                                
                                infoBox("Your Email has been received plz check your Email address to verify account", null, "Email Confirmation!");
                            }else{
                                infoBox("Username not Available../",null,"Error" );
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                else{        
                    infoBox("Plz type same passwords", null, "Password doesn't match!");      
                }    
            }while(pass2 == pass);
        }else{
            infoBox("Sign-Up Failed",null,"Error" );
        }
    }
    
    private void ExecuteQuery(String query) {      
        Connection conn = connectdb();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
    private void insertRecord(){
        String query = "INSERT INTO data VALUES (" + TFid.getText() + ",'" + TFemail.getText() + "','" + TFpassword.getText() + "','"
                + TFretypepassword.getText() + "')";
        ExecuteQuery(query);
    }
    
    
    
    public void loginAction(ActionEvent event){
        String email2 = textEmail.getText().toString();
        String password2 = textPassword.getText().toString();
    
        String sql = "SELECT * FROM data WHERE email = ? and password = ?";
        
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email2);
            preparedStatement.setString(2, password2);
            resultSet = preparedStatement.executeQuery();
            System.out.println("email = "+ email2 + " pass = "+ password2);
            if(!resultSet.next()){
                infoBox("Please enter correct Email and Password", null, "Failed");
            }else{
                infoBox("Login Successfull",null,"Success" );
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLMenu.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  //new code after this ...
    
    public void NoAccount(ActionEvent event){
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();
    
        String sql = "SELECT * FROM employee WHERE email = ? and password = ?";
        
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("You will be directed to the SignUp Page. Click OK to confirm", null, "No Account?             Don't Worry...!");
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }else{
                infoBox("Login Successfull",null,"Success" );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }  
    
}
