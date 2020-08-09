package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;
import sample.Database.Database_handler;
import sample.Model.Tasks;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Add_formController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Taskwork;

    @FXML
    private Button Add_button;

    @FXML
    private TextField Descr_box;

    @FXML
    private Button listButton;

    private int userId;
    private Database_handler database_handler;

    public Add_formController() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

    database_handler = new Database_handler();

    Tasks tasks=new Tasks();

    Add_button.setOnAction(event -> {
        Calendar calendar = Calendar.getInstance();

        java.sql.Timestamp timestamp =
                new java.sql.Timestamp(calendar.getTimeInMillis());
        String taskText = Taskwork.getText().trim();
        String taskDescription=Descr_box.getText().trim();
        if (!taskText.equals("") || !taskDescription.equals("")) {

            System.out.println("User Id: " + AddController.userId);

            tasks.setUserId(AddController.userId);
            tasks.setDatecreated(timestamp);
            tasks.setDescription(taskDescription);
            tasks.setTask_work(taskText);
            database_handler.insertTask(tasks);
        }


    });
        //int taskcount=database_handler.getTaskno(AddController.userId);
        //listButton.setText(listButton.getText() + "("+taskcount+")");
       listButton.setOnAction(event -> {
            listButton.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/List.fxml"));
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


    }
//    public int getUserId() {
//        System.out.println("from getUserId() " + userId);
//
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//        System.out.println("From setUserId " + this.userId);
//    }
//   Database_handler database_handler1 = new Database_handler();
//    int taskcount=database_handler1.getTaskno(AddController.userId);
//    sy
}
