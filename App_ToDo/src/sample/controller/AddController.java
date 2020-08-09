package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController {
    public static int userId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Warn;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView Add;

    @FXML
    private Button out;

    @FXML
    void initialize() {
        out.setOnAction(event -> {
            Warn.getScene().getWindow().hide();
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
   Add.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        Warn.relocate(500,300);
        Warn.setOpacity(0);

        Add.relocate(560,170);
        Add.setOpacity(0);
        try {
//            FXMLLoader loader=new FXMLLoader();
            AnchorPane ap= FXMLLoader.load(getClass().getResource("../View/Add_Event_Form.fxml"));
            AddController.userId = getUserId();
            rootPane.getChildren().addAll(ap);
        } catch (IOException e) {
            e.printStackTrace();
        }


   });
    }
    public void setUserId(int userId) {

        AddController.userId = userId;
        System.out.println("User Id is " + AddController.userId);

    }

    public int getUserId(){
        return userId;
    }

}
