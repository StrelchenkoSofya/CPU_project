package org.example.lab4.processor;

public class Memory {
    int size;
    int[] mem;

    public Memory(int s){
        this.size = s;
        this.mem = new int[size];
    }

    public int getSize() {
        return size;
    }

    public int get(int ind) {
        if(!(ind < 0 | ind > size))return this.mem[ind];
        return  0;
    }
    public void set(int ind, int val) {
        if(!(ind < 0 | ind > size))this.mem[ind] = val;
    }
}
