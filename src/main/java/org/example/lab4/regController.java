package org.example.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class regController {
    @FXML
    Label ind;
    @FXML
    Label val;

    public void setReg(int in, int v)
    {
        if(in==0)ind.setText("A");
        else if(in==1)ind.setText("B");
        else if(in==2)ind.setText("C");
        else if(in==3)ind.setText("D");
        else ind.setText("?");
        val.setText(Integer.toString(v));
    }
}
