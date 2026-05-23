package org.example.lab4.processor;

public class Executer {
    ICpu cpu;
    int ind = 0;

    public Executer(ICpu cpu){
        this.cpu = cpu;
    }

    public void run(Program program) throws CpuException {
        ind++;
        try{
            for(Command i: program){
                cpu.exec(i);
                ind++;
            }
        } catch(CpuException e){
            throw new CpuException("String " + ind + ". " + e.getMessage());
        }
    }

    public void runOnce(Program program) throws CpuException {
        try{
            cpu.exec(program.getElement(ind));
            ind++;
        } catch(CpuException e){
            throw new CpuException("String " + (ind+1) + ". " + e.getMessage());
        }
    }

}
