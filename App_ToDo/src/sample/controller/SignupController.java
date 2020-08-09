package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Database_handler;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private RadioButton MaleRB;

    @FXML
    private RadioButton FemaleRB;

    @FXML
    private TextField uname;

    @FXML
    private PasswordField passwrd;

    @FXML
    private TextField loc;

    @FXML
    private Button Signbutton;

    @FXML
    private Button LogButton;

    @FXML
    void initialize() {
        LogButton.setOnAction(event -> {
            Signbutton.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/Login.fxml"));
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

        Signbutton.setOnAction(event -> {

            createUser();


            Signbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));


            try {
                loader.load();


            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();



        });


    }

    private void createUser() {

        Database_handler databaseHandler = new Database_handler();


        String name = fname.getText();
        String lastName = lname.getText();
        String userName = uname.getText();
        String password = passwrd.getText();
        String location = loc.getText();


        String gender;
        if (FemaleRB.isSelected()) {
            gender = "Female";
        } else gender = "Male";
        User user = new User(name, lastName,gender, userName, password, location);




        databaseHandler.signUpUser(user);


    }



    }

