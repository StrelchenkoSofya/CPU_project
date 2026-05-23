package org.example.lab4.processor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandDAO_H extends CommandDAO{
    Session session = null;
    Transaction transaction = null;


    @Override
    public void add(Command c){
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(c);
            transaction.commit();
            commands.add(c);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void del(Command c){
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.remove(c);
            transaction.commit();
            commands.remove(c);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void move_r(Command c){
        try {
            int ind = commands.indexOf(c);
            int s=commands.size();
            if(ind!=s-1){
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                ArrayList<Command> c1=commands;
                Command to_left = c1.get(c1.indexOf(c) + 1);
                String tmp = c.getCommand();
                c.setCommand(to_left.getCommand());
                to_left.setCommand(tmp);
                tmp = c.getArg1();
                c.setArg1(to_left.getArg1());
                to_left.setArg1(tmp);
                tmp = c.getArg2();
                c.setArg2(to_left.getArg2());
                to_left.setArg2(tmp);
                session.merge(c);
                session.merge(to_left);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void move_l(Command c){
        try {
            int ind = commands.indexOf(c);
            int s=commands.size();
            if(ind!=0){
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                ArrayList<Command> c1=commands;
                Command to_left = c1.get(c1.indexOf(c) - 1);
                String tmp = c.getCommand();
                c.setCommand(to_left.getCommand());
                to_left.setCommand(tmp);
                tmp = c.getArg1();
                c.setArg1(to_left.getArg1());
                to_left.setArg1(tmp);
                tmp = c.getArg2();
                c.setArg2(to_left.getArg2());
                to_left.setArg2(tmp);
                session.merge(c);
                session.merge(to_left);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Command> return_com(){
        ArrayList<Command> all_commands = super.return_com();
        all_commands.clear();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            List<Command> commands = session.createQuery("FROM Command", Command.class).getResultList();
            commands.forEach(all_commands::add);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return all_commands;
    }
}
