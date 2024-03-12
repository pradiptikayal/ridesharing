package com.ridesharing.Processor;

import java.io.BufferedReader;
import java.io.FileReader;

public class Tasks{
    
    String fileName;
    public Tasks(String fileName)
    {
        this.fileName = fileName;
    }
    
    public void runTasks() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String st;
            Processor pr = new Processor();
            while((st = br.readLine()) != null)
            {
                pr.setCommand(st);
                pr.processCommand();
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
};
