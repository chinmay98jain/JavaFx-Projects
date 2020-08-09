package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Database_handler;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    private int userId;
    @FXML
    private Label wc_label;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label cred_label;

    @FXML
    private TextField username;

    @FXML
    private PasswordField passwrd;

    @FXML
    private Button login_but;

    @FXML
    private Label title_label;

    @FXML
    private Button signin_but;

    private Database_handler database_handler;

    @FXML
    void initialize() {
        database_handler= new Database_handler();
        signin_but.setOnAction(event -> {
            login_but.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/Signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });

        login_but.setOnAction(event -> {
            String uname=username.getText().trim();
            String pwd=passwrd.getText().trim();
            User user = new User();
            user.setUserName(uname);
            user.setPassword(pwd);
            ResultSet rs=database_handler.checkUser(user);
            int counter=0;
            try{
            while (rs.next()) {
                counter++;
                userId = rs.getInt("UserId");
            }
            if(counter==1)
            {
                    login_but.getScene().getWindow().hide();
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/View/Add_Event.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root=loader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(root));
                    AddController addController = loader.getController();
                    addController.setUserId(userId);
                    stage.show();
            }
            else {
                wc_label.setText("Wrong Entries !!");
                username.setText("");
                passwrd.setText("");
            }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }



        });

    }


}
