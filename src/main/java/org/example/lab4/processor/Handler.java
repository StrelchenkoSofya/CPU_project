package org.example.lab4.processor;

class Handler {
    Handler next;

    void run(Command command, Cpu cpu) throws CpuException {
        if(next != null)
        {
            next.run(command, cpu);
        }
        else {
            throw new CpuException("This command is not exist");
        }
    }

    Handler add(Handler next){
        this.next = next;
        return next;
    }
}