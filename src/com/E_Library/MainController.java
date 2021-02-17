/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.E_Library;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author mianx
 */
public class MainController implements Initializable {
    
    @FXML
    private VBox vbox;             //imported from frontend to apply logic on it... 
    @FXML
    private Parent fxml;           //imported from frontend to apply logic on it...
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfretypepassword;
    
    //login and signup code starts from here
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);   //imported class from javafx.animation
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));     //this loads SignIn form in fxml variable
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch(IOException ex){
                
            }
        });
    }
    @FXML                                                   // opening signin event
    private void open_signin(ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);   //imported class from javafx.animation
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));     //this loads SignIn form in fxml variable
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch(IOException ex){
                
            }
        });
    }
     @FXML                                                 // opening signup event
    private void open_signup(ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);   //imported class from javafx.animation
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {
            try{
                fxml = FXMLLoader.load(getClass().getResource("SignUp.fxml"));     //this loads SignIn form in fxml variable
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch(IOException ex){
                
            }
        });
    }
}