package org.example.lab4.processor;

public class BCpu {
    static Cpu cpu;

    public static ICpu build(){
        if(cpu == null){
            cpu = new Cpu();
            cpu.getHandler().add(new BaseHandler());
        }
        return cpu;
    }

}