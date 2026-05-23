package org.example.lab4.processor;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "all_commands")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "command", nullable = false)
    private String command;
    @Column(name = "arg1")
    private String arg1;
    @Column(name = "arg2")
    private String arg2;

    public int getId() {
        return id;
    }

    public Command(){}

    public Command(String command) {
        this.command = command;
        this.arg1 = "";
        this.arg2 = "";
    }

    public Command(String command, String arg1, String arg2) {
        this.command = command;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Command(int id, String command, String arg1, String arg2) {
        this.id=id;
        this.command = command;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String getArg1(){
        return arg1;
    }
    public String getArg2(){
        return arg2;
    }
    public String getCommand() {
        return this.command;
    }

    public void setArg1(String a){
        arg1=a;
    }
    public void setArg2(String a){
        arg2 = a;
    }
    public void setCommand(String a) {
        this.command = a;
    }

    @Override
    public String toString(){
        return command + " " + arg1 + " " + arg2 + " ";
    }
}
