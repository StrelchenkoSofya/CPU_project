package org.example.lab4.processor;

import java.util.ArrayList;
import java.util.Iterator;

public class Model implements Iterable<Command>{
    Command active = null;
    Program prog = new Program();
    ICpu cpu = BCpu.build();
    Executer exec = new Executer(cpu);
    ArrayList<IObserver> allO = new ArrayList<>();

    void event(){
        allO.forEach(action->action.event());
    }
    public void addListener(IObserver o){
        allO.add(o);
        event();
    }

    public Command getActive(){return active;}
    public int getMemSize(){return cpu.getMemSize();}
    public int getRegSize(){return cpu.getSize();}
    public void add(Command c){prog.add(c); event();}
    public void del(Command c){prog.del(c); event();}
    public void moveLeft(Command c){prog.move_l(c); event();}
    public void moveRight(Command c){prog.move_r(c); event();}
    public void doOnce() throws CpuException {
        exec.runOnce(prog);
        active=prog.commands.return_com().get(prog.commands.return_com().indexOf(active) + 1);
        event();
    }
    public void restart(){
        active=null;
        cpu.clear();
        cpu = null;
        cpu = BCpu.build();
        exec = new Executer(cpu);
        event();
    }
    public int mem(int ind) {return cpu.getCellMem(ind);}
    public int reg(int ind) {return cpu.getReg(ind);}
    public String statMem(){return prog.returnMem();}
    public String statMostPopInst(){return prog.mostPopularInstruction();}
    public ArrayList<String> statInst(){return prog.mostPopularInstructions();}



    @Override
    public Iterator<Command> iterator() {
        return prog.iterator();
    }
}
