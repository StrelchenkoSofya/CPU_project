package org.example.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.lab4.processor.*;

import java.io.IOException;

public class commandController {
    @FXML
    Label label_com;
    @FXML
    Label label_arg1;
    @FXML
    Label label_arg2;

    Model m = BModel.build();
    Command c;
    boolean active=false;

    public void active(boolean b){
        active=b;
    }

    public void setCommand(Command c)
    {
        this.c = c;
        label_com.setText(c.getCommand());
        label_arg1.setText(c.getArg1());
        label_arg2.setText(c.getArg2());
        if(active){
            label_com.setStyle("-fx-text-fill: red;");
            label_arg1.setStyle("-fx-text-fill: red;");
            label_arg2.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    void delCommand(){
        m.del(c);
    }
    @FXML
    void moveLeft(){
        m.moveLeft(c);
    }
    @FXML
    void moveRight(){
        m.moveRight(c);
    }

}