package org.example.lab4.processor;

public class HandlerMin extends Handler{
    @Override
    void run(Command command, Cpu cpu) throws CpuException {
        switch (command.getCommand())
        {
            case "min" -> {
                if(!command.getArg1().equals(" ") | !command.getArg2().equals(" ")) throw new CpuException("Arguments should not exist");
                int min = cpu.getReg(0);
                for(int i = 1; i< cpu.getSize(); i++){
                    int r = cpu.getReg(i);
                    if(r < min) min = r;
                }
                cpu.setReg(3, min);
            }

            default ->  super.run(command, cpu);
        }
    }
}