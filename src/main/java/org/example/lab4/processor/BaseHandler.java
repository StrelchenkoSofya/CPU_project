package org.example.lab4.processor;

public class BaseHandler extends Handler{
    @Override
    void run(Command command, Cpu cpu) throws CpuException {
        switch (command.getCommand())
        {

            case "ld" -> {
                try{
                    Integer.parseInt(command.getArg2());
                } catch(Exception e) {
                    throw new CpuException("Second argument is wrong");
                }
                int ind = Integer.parseInt(command.getArg2());
                if(ind < 0 | ind > cpu.getMem().getSize())throw new CpuException("Second argument is wrong");
                switch (command.getArg1()){
                    case "a" -> cpu.setReg(0, cpu.getCellMem(ind));
                    case "b" -> cpu.setReg(1, cpu.getCellMem(ind));
                    case "c" -> cpu.setReg(2, cpu.getCellMem(ind));
                    case "d" -> cpu.setReg(3, cpu.getCellMem(ind));
                    default -> throw new CpuException("First argument is wrong");
                }
            }

            case "st" -> {
                try{
                    Integer.parseInt(command.getArg2());
                } catch(Exception e) {
                    throw new CpuException("Second argument is wrong");
                }
                int ind = Integer.parseInt(command.getArg2());
                if(ind < 0 | ind > cpu.getMem().getSize())throw new CpuException("Second argument is wrong");
                switch (command.getArg1()){
                    case "a" -> cpu.getMem().set(ind, cpu.getReg( 0));
                    case "b" -> cpu.getMem().set(ind, cpu.getReg( 1));
                    case "c" -> cpu.getMem().set(ind, cpu.getReg( 2));
                    case "d" -> cpu.getMem().set(ind, cpu.getReg( 3));
                    default -> throw new CpuException("First argument is wrong");
                }
            }

            case "mv" -> {
                int ind1 = -1;
                int ind2 = -1;
                switch (command.getArg1()){
                    case "a" -> ind1 = 0;
                    case "b" -> ind1 = 1;
                    case "c" -> ind1 = 2;
                    case "d" -> ind1 = 3;
                    default -> throw new CpuException("First argument is wrong");
                }
                switch (command.getArg2()){
                    case "a" -> ind2 = 0;
                    case "b" -> ind2 = 1;
                    case "c" -> ind2 = 2;
                    case "d" -> ind2 = 3;
                    default -> throw new CpuException("Second argument is wrong");
                }
                cpu.setReg(ind1, cpu.getReg(ind2));
            }

            case "init" -> {
                try{
                    Integer.parseInt(command.getArg1());
                } catch(Exception e) {
                    throw new CpuException("First argument is wrong");
                }
                try{
                    Integer.parseInt(command.getArg2());
                } catch(Exception e) {
                    throw new CpuException("Second argument is wrong");
                }
                int ind = Integer.parseInt(command.getArg1());
                int val = Integer.parseInt(command.getArg2());
                if(ind < 0 | ind > cpu.getMem().getSize())throw new CpuException("First argument is wrong");
                cpu.getMem().set(ind, val);
            }

            /*case "print" -> {
                if(!command.getArg1().isEmpty() | !command.getArg2().isEmpty()) throw new CpuException("Arguments should not exist");
                System.out.println();
                for(int i = 0; i < cpu.getSize(); i++){
                    System.out.print(cpu.getReg(i) + " ");
                }
                System.out.println("\n");
            }*/

            case "add" -> {
                if(!command.getArg1().isEmpty() | !command.getArg2().isEmpty()) throw new CpuException("Arguments should not exist");
                cpu.setReg(3, cpu.getReg(0) + cpu.getReg(1));
            }

            case "sub" -> {
                if(!command.getArg1().isEmpty() | !command.getArg2().isEmpty()) throw new CpuException("Arguments should not exist");
                cpu.setReg(3, cpu.getReg(0) - cpu.getReg(1));
            }

            case "mult" -> {
                if(!command.getArg1().isEmpty() | !command.getArg2().isEmpty()) throw new CpuException("Arguments should not exist");
                cpu.setReg(3, cpu.getReg(0) * cpu.getReg(1));
            }

            case "div" -> {
                if(!command.getArg1().isEmpty() | !command.getArg2().isEmpty()) throw new CpuException("Arguments should not exist");
                cpu.setReg(3, cpu.getReg(0) / cpu.getReg(1));
            }


            default ->  super.run(command, cpu);
        }
    }
}
