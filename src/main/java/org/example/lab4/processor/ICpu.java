package org.example.lab4.processor;

public interface ICpu {
    void exec(Command command) throws CpuException;
    int getCellMem(int ind);
    int getReg(int ind);
    int getSize();
    int getMemSize();
    void clear();
}
