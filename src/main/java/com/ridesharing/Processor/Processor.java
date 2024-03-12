package com.ridesharing.Processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ridesharing.Commands.AddDriver;
import com.ridesharing.Commands.AddRider;
import com.ridesharing.Commands.Commander;
import com.ridesharing.Commands.GenerateBill;
import com.ridesharing.Commands.Match;
import com.ridesharing.Commands.StartRide;
import com.ridesharing.Commands.StopRide;
import com.ridesharing.Model.Driver;
import com.ridesharing.Model.Rider;
import com.ridesharing.RideInfo.RideDetails;

import lombok.Setter;
import lombok.Getter;


public class Processor {
    
    @Setter String command;
    @Getter List<Driver> driverList = new ArrayList<>();
    @Getter List<Rider> riderList = new ArrayList<>();
    @Getter Map<Rider, List<Driver>> riderToNearestDrivers = new HashMap<>();
    Map<String,RideDetails> ridesOngoing = new HashMap<>();

    public void processCommand()
    {
        String arr[] = this.command.split(" ");
        String currCommand  = arr[0];
        
        switch(currCommand)
        {
                case "ADD_DRIVER": String id = arr[1];
                                   int xCordinate = Integer.parseInt(arr[2]);
                                   int yCordinate = Integer.parseInt(arr[3]);
                                   Commander ad = new AddDriver(id, xCordinate, yCordinate,driverList);
                                   ad.performCommand();
                                    break;
                case "ADD_RIDER":  String rdid = arr[1];
                                   int rXCord = Integer.parseInt(arr[2]);
                                   int rYCord = Integer.parseInt(arr[3]);
                                   Commander ar = new AddRider(rdid, rXCord, rYCord, riderList);
                                   ar.performCommand();
                                    break;  
                case "MATCH": String riderId = arr[1];
                              Commander mat = new Match(riderId, driverList, riderList,riderToNearestDrivers);
                              mat.performCommand();                            
                              break;
                case "START_RIDE": String rideId = arr[1];
                                   int nthDriver = Integer.parseInt(arr[2]);
                                   String rdrid = arr[3];
                                   Commander sr = new StartRide(rideId, nthDriver, rdrid, riderList, riderToNearestDrivers, ridesOngoing, driverList);
                                   sr.performCommand();
                                   break;
                case "STOP_RIDE": String rId = arr[1];
                                  int xCord = Integer.parseInt(arr[2]);
                                  int yCord = Integer.parseInt(arr[3]);
                                  int time = Integer.parseInt(arr[4]);
                                  Commander st = new StopRide(rId, ridesOngoing, xCord, yCord, time);
                                  st.performCommand();
                                  break;
                case "BILL": String rideid = arr[1];
                             Commander gb = new GenerateBill(rideid, ridesOngoing);
                             gb.performCommand();
                            break;                
                default: System.out.println("COMMAND_NOT_FOUND");
        };
           
    }
}
