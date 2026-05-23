package org.example.lab4.processor;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Program implements Iterable<Command>{
   // ArrayList<Command> commands = new ArrayList<Command>();
    CommandDAO commands = new CommandDAO_H();
    int size = 0;

    public Program(){}

    public int getSize() {
        return size;
    }
    public Command getElement(int ind){
        return commands.return_com().get(ind);
    }

    public void add(Command command){
        commands.add(command);
        size++;
    }
    public void del(Command command){
        commands.del(command);
        size--;
    }
    public void move_r(Command command){
        commands.move_r(command);
    }
    public void move_l(Command command){
        commands.move_l(command);
    }

    public String returnMem() {
        /*HashSet<Integer> memSet = new HashSet<Integer>();
        int c = 0;
        try{
            for (Command i : commands) {
                c++;
                switch (i.getCommand()) {
                    case "ld" -> {
                        memSet.add(Integer.parseInt(i.getArg2()));
                    }
                    case "st" -> {
                        memSet.add(Integer.parseInt(i.getArg2()));
                    }
                    case "init" -> {
                        memSet.add(Integer.parseInt(i.getArg1()));
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("String " + c + ": Memory error");
        }
        ArrayList<Integer> memList = new ArrayList<Integer>(memSet);
        Collections.sort(memList);
        return memList;*/
        if(commands.return_size()==0)return "";
        ArrayList<Integer> arr1 = commands.return_com().stream().
                filter((a) -> a.getCommand().equals("init")).collect(
                ()->new ArrayList<Integer>(),
                (list,item)->list.add(parseInt(item.getArg1())),
                (list1,list2)->list1.addAll(list2));
        ArrayList<Integer> arr2 = commands.return_com().stream().
                filter((a) -> a.getCommand().equals("st") | a.getCommand().equals("ld")).collect(
                        ()->new ArrayList<Integer>(),
                        (list,item)->list.add(parseInt(item.getArg2())),
                        (list1,list2)->list1.addAll(list2));
        arr1.addAll(arr2);
        if(arr1.isEmpty()){
            return "";
        }
        return arr1.stream().min(Comparator.comparing(Integer::valueOf)).get()
                + " - " + arr1.stream().max(Comparator.comparing(Integer::valueOf)).get();
    }

    public String mostPopularInstruction(){
        /*String res = "";
        ArrayList<String> str_com = new ArrayList<>();
        for(Command i: commands){
            str_com.add(i.getCommand());
        }
        res = Collections.max(str_com, new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return Collections.frequency(str_com, o1) - Collections.frequency(str_com, o2);
            }
        });
        return res;*/
        if(commands.return_size()==0)return "";
        ArrayList<String> str = commands.return_com().stream().collect(
                ()->new ArrayList<String>(),
                (list,item)->list.add(item.getCommand()),
                (list1,list2)->list1.addAll(list2));
        Command res = commands.return_com().stream()
                .max((c1,c2)->Collections.frequency(str,c1.getCommand()) - Collections.frequency(str,c2.getCommand()))
                .get();
        return res.getCommand();
    }

    public ArrayList<String> mostPopularInstructions(){
        /*ArrayList<String> str_com = new ArrayList<String>();
        HashSet<String> t = new HashSet<String>();
        for(Command i: commands){
            str_com.add(i.getCommand());
            t.add(i.getCommand());
        }
        ArrayList<String> res = new ArrayList<String>(t);
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collections.frequency(str_com, o1) - Collections.frequency(str_com, o2);
            }
        });
        return res;*/
        if(commands.return_size()==0)return null;
        ArrayList<String> str = commands.return_com().stream().collect(
                ()->new ArrayList<String>(),
                (list,item)->list.add(item.getCommand()),
                (list1,list2)->list1.addAll(list2));
        HashSet<String> r = new HashSet<>(str);
        List<String> res1 = r.stream()
                .sorted((c1,c2)->Collections.frequency(str,c1)-Collections.frequency(str,c2))
                .collect(()->new ArrayList<String>(),
                        (list,item)->list.add(item),
                        (list1,list2)->list1.addAll(list2)).reversed();
        ArrayList<String> res = new ArrayList<>(res1);
        return res;
    }

    @Override
    public Iterator<Command> iterator() {
        /*return new Iterator<Command>(){
            int curr = 0;

            @Override
            public boolean hasNext(){
                return curr < getSize();
            }

            @Override
            public Command next(){
                if(!hasNext())throw new RuntimeException("Command is not exist");
                curr++;
                return getElement(curr - 1);
            }
        };*/
        return commands.return_com().iterator();
    }
}
