package com.ridesharing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("src/main/resources/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(path.toAbsolutePath().toString()));
        String st;
        //TODO::implement multithreading
        //TODO::implement person interfacr / abstracr class for driver and rider
        Processor pr = new Processor();
        while((st = br.readLine()) != null)
        {
            pr.setCommand(st);
            pr.processCommand();
        }
        br.close();
    }
}
