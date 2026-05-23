package org.example.lab4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.lab4.processor.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class progController implements IObserver {
    Model m = BModel.build();

    @FXML
    GridPane all_commands;
    @FXML
    GridPane all_mem;
    @FXML
    GridPane all_reg;

    @FXML
    TextField text_com;
    @FXML
    TextField text_arg1;
    @FXML
    TextField text_arg2;

    @FXML
    Label mem_stat;
    @FXML
    Label mpp_stat;
    @FXML
    Label reit_stat;

    @FXML
    void initialize()
    {
        m.addListener(this);
    }

    @FXML
    void addCommand(){
        Command c = new Command(text_com.getText(), text_arg1.getText(), text_arg2.getText());
        m.add(c);
    }

    @FXML
    void restart(){
        m.restart();
    }

    @FXML
    void doOnce() throws CpuException {
        m.doOnce();
    }

    @Override
    public void event() {
        all_commands.getChildren().clear();
        all_mem.getChildren().clear();
        all_reg.getChildren().clear();

        mem_stat.setText(m.statMem());
        mpp_stat.setText(m.statMostPopInst());
        ArrayList<String> inst = m.statInst();
        String str="";
        if(inst!=null){
            for(String i:inst){
                str=str+i+"\n";
            }
        }
        reit_stat.setText(str);

        for (Command c : m) {
            commandController cc = new commandController();
            if(m.getActive()==c){
                cc.active(true);
            }
            else{
                cc.active(false);
            }
            FXMLLoader fxmlLoader = new FXMLLoader(
                    progController.class.getResource("viewCommand.fxml"));
            fxmlLoader.setController(cc);
            try {
                Pane pane = fxmlLoader.load();
                cc.setCommand(c);
                all_commands.addColumn(0, pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        int s=m.getMemSize();
        int count = 0;
        for(int i=0; i<s;i++){
            memController mc = new memController();
            FXMLLoader fxmlLoader = new FXMLLoader(
                    progController.class.getResource("viewMem.fxml"));
            fxmlLoader.setController(mc);
            try {
                Pane pane = fxmlLoader.load();
                mc.setMem(i, m.mem(i));
                if((i%5==0)&&(i!=0)){
                    count++;
                    all_mem.addColumn(0, pane);
                }
                else{
                    all_mem.addRow(count, pane);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        s=m.getRegSize();
        for(int i=0; i<s;i++){
            regController rc = new regController();
            FXMLLoader fxmlLoader = new FXMLLoader(
                    progController.class.getResource("viewReg.fxml"));
            fxmlLoader.setController(rc);
            try {
                Pane pane = fxmlLoader.load();
                rc.setReg(i, m.reg(i));
                all_reg.addRow(0, pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
