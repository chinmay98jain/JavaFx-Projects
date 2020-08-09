package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Database_handler;
import sample.Model.Tasks;
import sun.security.krb5.internal.crypto.Des;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Tasks> ListBox;

    @FXML
    private TextField TaskBox;

    @FXML
    private TextField DescBox;

    @FXML
    private Button AddButton;

    @FXML
    private Button Out;

    private ObservableList<Tasks> tasks;
    private Database_handler database_handler;


    @FXML
    void initialize() throws SQLException {
        database_handler = new Database_handler();
        tasks = FXCollections.observableArrayList();

        Out.setOnAction(event -> {
            AddButton.getScene().getWindow().hide();
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


        AddButton.setOnAction(event -> {
            try {
                Add_again();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        ResultSet resultSet = database_handler.getTasksByUser(AddController.userId);

        while (resultSet.next()) {
            Tasks task = new Tasks();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask_work(resultSet.getString("Task_work"));
            task.setDatecreated(resultSet.getTimestamp("DateCreated"));
            task.setDescription(resultSet.getString("Description"));

            tasks.addAll(task);

        }
        ListBox.setItems(tasks);
        ListBox.setCellFactory(CellController -> new CellController());



    }
    public void Add_again() throws SQLException { database_handler = new Database_handler();
        Tasks tasks1=new Tasks();
        Calendar calendar = Calendar.getInstance();

        java.sql.Timestamp timestamp =
                new java.sql.Timestamp(calendar.getTimeInMillis());
        String taskText = TaskBox.getText().trim();
        String taskDescription=DescBox.getText().trim();
        if (!taskText.equals("") || !taskDescription.equals("")) {

            System.out.println("User Id: " + AddController.userId);

            tasks1.setUserId(AddController.userId);
            tasks1.setDatecreated(timestamp);
            tasks1.setDescription(taskDescription);
            tasks1.setTask_work(taskText);
            database_handler.insertTask(tasks1);
        }
        TaskBox.setText("");
        DescBox.setText("");
        initialize();
    }

    }

