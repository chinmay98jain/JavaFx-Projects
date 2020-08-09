package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.Database.Database_handler;
import sample.Model.Tasks;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CellController extends ListCell<Tasks> {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private AnchorPane rootpane;

    @FXML
    private Label TaskLabel;

    @FXML
    private Label DescLabel;

    @FXML
    private Label DateLabel;

    @FXML
    private ImageView DelBut;

    private FXMLLoader fxmlLoader;

    private Database_handler databaseHandler;


    @FXML
    void initialize() throws SQLException {

    }
    @Override
    public void updateItem(Tasks myTask, boolean empty) {

        databaseHandler = new Database_handler(); //main change

        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {

            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/view/Cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            TaskLabel.setText(myTask.getTask_work());
            DateLabel.setText(myTask.getDatecreated().toString());
            DescLabel.setText(myTask.getDescription());

            int taskId = myTask.getTaskId();
            DelBut.setOnMouseClicked(event -> {
                try {
                    databaseHandler.deleteTask(taskId);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                getListView().getItems().remove(getItem());
            });
            setText(null);
            setGraphic(rootpane);
        }
    }
}

//
//postsListView.setCellFactory(param-> new ListCell<Post>(){
//@Override
//protected void updateItem(Post post,boolean empty){
//        super.updateItem(post,empty);
//        if(empty || post==null) {
//        setText(null);
//        setGraphic(null);
//        }
//        else{
//        HBox root = GUIElementsGenerator.getHBoxRoot(); //change
//        VBox commonItems = GUIElementsGenerator.getCommonItemsInVBox(post);
//        root.getChildren().add(GUIElementsGenerator.getImageNode(post));
//        if(post instanceof EventPost){
//        VBox eventItems = GUIElementsGenerator.getEventItemsInVBox((EventPost)post);
//        root.getChildren().addAll(new ImageView(),commonItems,eventItems);
//        root.setStyle("-fx-background-color: lightblue;");
//        }
//        else if(post instanceof SalePost){
//        VBox saleItems = GUIElementsGenerator.getSaleItemsInVBox((SalePost)post,username);
//        root.getChildren().addAll(new ImageView(),commonItems,saleItems);
//        root.setStyle("-fx-background-color: lightpink;");
//        }
//        else{
//        VBox jobItems = GUIElementsGenerator.getJobItems((JobPost)post);
//        root.getChildren().addAll(new ImageView(),commonItems,jobItems);
//        root.setStyle("-fx-background-color: lightyellow;");
//        }
//        Region region = new Region();
//        HBox.setHgrow(region, Priority.ALWAYS);
//        root.getChildren().add(region);
//        Button replyBtn = getReplyButton(post);
//        Button detailsBtn = getDetailsButton(post);
//        root.getChildren().add(replyBtn);
//        if (post.getCreator_ID().equalsIgnoreCase(username))
//        root.getChildren().add(detailsBtn);
//        setText(null);
//        setGraphic(root);
//        }
//        }
//        });