package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Model.Operations;

import java.net.URL;
import java.util.ResourceBundle;

public class Calc_controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label op;

    @FXML
    private Button nine;

    @FXML
    private Button eight;

    @FXML
    private Button seven;

    @FXML
    private Button divide;

    @FXML
    private Button six;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button multiply;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button one;

    @FXML
    private Button sub;

    @FXML
    private Button clear;

    @FXML
    private Button zero;

    @FXML
    private Button eql;

    @FXML
    private Button add;

    private long n1=0;
    private String operator="";
    private boolean start=true;
    private Operations op1=new Operations();

    public void Clr(ActionEvent event)
    {
        op.setText("");
    }
    public void Numbers(ActionEvent event)
    {
        if(start) {
            op.setText("");
            start = false;
        }
            String value=((Button)event.getSource()).getText();
            op.setText(op.getText()+value);

    }
    public void Operate(ActionEvent event)
    {
        String value=((Button)event.getSource()).getText();
        if(!value.equals("="))
        {
            if(!operator.isEmpty())
                return;
            operator=value;
            n1=Long.parseLong(op.getText());
            op.setText("");
        }
        else
        {
            if(operator.isEmpty())
                return;
            long n2=Long.parseLong(op.getText());
            double res=op1.oprtn(n1,n2,operator);
            op.setText(String.valueOf(res));
            operator="";
            start=true;
        }
    }
}
