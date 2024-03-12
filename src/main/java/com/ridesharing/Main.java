package com.ridesharing;

import java.io.IOException;

import com.ridesharing.Processor.Tasks;

public class Main{
    public static void main(String[] args) throws IOException{
        Tasks r = new Tasks(args[0]);
        r.runTasks();   
    }
}
