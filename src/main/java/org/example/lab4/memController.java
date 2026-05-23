package org.example.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.lab4.processor.BModel;
import org.example.lab4.processor.Command;
import org.example.lab4.processor.Model;

public class memController {
    @FXML
    Label ind;
    @FXML
    Label val;

    public void setMem(int in, int v)
    {
        ind.setText(Integer.toString(in));
        val.setText(Integer.toString(v));
    }
}
