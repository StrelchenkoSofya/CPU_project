package org.example.lab4.processor;

import java.util.ArrayList;
import java.util.Collections;

public class CommandDAO {
    ArrayList<Command> commands = new ArrayList<Command>();

    public int return_size(){
        return commands.size();
    }

    public void add(Command c){
        commands.add(c);
    }

    public void del(Command c){
        commands.remove(c);
    }

    public void move_r(Command command){
        int ind = commands.indexOf(command);
        if(ind!=commands.size()-1){
            Collections.swap(commands, ind, ind+1);
        }
    }
    public void move_l(Command command){
        int ind = commands.indexOf(command);
        if(ind!=0){
            Collections.swap(commands, ind, ind-1);
        }
    }

    public ArrayList<Command> return_com(){
        return commands;
    }
}
