package org.example.lab4.processor;

public class Cpu implements ICpu{
    int memsize = 1024;
    Memory mem = new Memory(memsize);
    int size = 4;
    int[] reg = new int[size];
    Handler handler = new Handler();

    public Cpu(){}

    public Handler getHandler() {
        return handler;
    }

    @Override
    public void clear(){
        for(int i=0;i<memsize;i++){
            mem.set(i,0);
        }
        for(int i=0;i<size;i++){
            reg[i]=0;
        }
    }

    @Override
    public int getSize() {
        return size;
    }
    @Override
    public int getMemSize(){return memsize;}

    public Memory getMem(){
        return mem;
    }

    @Override
    public int getCellMem(int ind) {
        return mem.get(ind);
    }

    @Override
    public int getReg(int ind) {
        if(!(ind < 0 | ind > size))return reg[ind];
        return 0;
    }
    public void setReg(int ind, int val) {
        if(!(ind < 0 | ind > size))reg[ind] = val;
        return;
    }

    @Override
    public void exec(Command command) throws CpuException {
        handler.run(command, this);
    }
}
