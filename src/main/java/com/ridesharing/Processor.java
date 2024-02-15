package com.ridesharing;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Setter;


public class Processor {
    
    @Setter String command;
    List<Driver> driverList = new ArrayList<>();
    List<Rider> riderList = new ArrayList<>();
    Map<Rider, List<Driver>> riderToNearestDrivers = new HashMap<>();
    Map<String,RideDetails> ridesOngoing = new HashMap<>();

    void processCommand()
    {
        String arr[] = this.command.split(" ");
        String currCommand  = arr[0];
        
        switch(currCommand)
        {
                case "ADD_DRIVER": Driver d = new Driver(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                                    this.driverList.add(d);
                                    break;
                case "ADD_RIDER":  Rider r = new Rider( arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                                    this.riderList.add(r);
                                    break;  
                case "MATCH": String riderId = arr[1];
                              //TODO:: handle use case when list is empty  
                              Rider rider = riderList.stream().filter(a -> a.id.equals(riderId)).collect(Collectors.toList()).get(0);
                              Matcher matcher = new Matcher(this.driverList);
                              List<Driver> driversMatched = matcher.getMatchedDrivers(rider);
                              if(driversMatched.size()> 0)
                                System.out.print("DRIVERS_MATCHED ");
                              else
                                System.out.println("NO_DRIVERS_AVAILABLE");  
                              int k =1;  
                              for(Driver driver : driversMatched) {
                                if( k > 5)
                                    break;
                                System.out.print(driver.id+" ");
                                k++;    
                              }
                              riderToNearestDrivers.put(rider, driversMatched);
                              break;
                case "START_RIDE": String rideId = arr[1];
                                   int nthDriver = Integer.parseInt(arr[2]);
                                   String rdrid = arr[3];
                                   //TODO::handle use case when list is empty
                                   Rider rdr = riderList.stream().filter(x -> x.id.equals(rdrid)).collect(Collectors.toList()).get(0);
                                   List<Driver> matchedDrvs = riderToNearestDrivers.get(rdr);
                                   if(matchedDrvs.size()<nthDriver || ridesOngoing.containsKey(rideId)) 
                                   {
                                        System.out.println("INVAILD_RIDE");
                                   }
                                   else
                                   {
                                    //TODO::handle use case when list is empty
                                    Driver drv = driverList.stream().filter( y -> y.id.equals(matchedDrvs.get(nthDriver-1).getId())).collect(Collectors.toList()).get(0);
                                    if(!drv.isAvailable())
                                    System.out.println("INVAILD_RIDE");
                                    else
                                    {
                                        drv.setAvailable(false);
                                        RideDetails rd = new RideDetails(rideId, rdr, drv);
                                        ridesOngoing.put(rideId, rd);
                                        System.out.println("RIDE_STARTED "+ rideId);
                                    }
                                   }
                                   break;
                case "STOP_RIDE": String rId = arr[1];
                                  int xCord = Integer.parseInt(arr[2]);
                                  int yCord = Integer.parseInt(arr[3]);
                                  int time = Integer.parseInt(arr[4]);
                                  RideDetails rdetail = ridesOngoing.containsKey(rId) ? ridesOngoing.get(rId) : null;
                                  if(rdetail == null || rdetail.getStatus().equals(RideStatus.COMPLETED))
                                   System.out.println("INVALID_RIDE");
                                  else
                                  { 
                                  rdetail.setDestXCord(xCord);
                                  rdetail.setDestYCord(yCord);
                                  rdetail.setTime(time);
                                  rdetail.setStatus(RideStatus.COMPLETED);  
                                  ridesOngoing.put(rId, rdetail);
                                  System.out.println("RIDE_STOPPED "+ rId);
                                  }
                                  break;
                case "BILL": String rideid = arr[1];
                            DecimalFormat df = new DecimalFormat("#.##");
                             RideDetails rdeDetails = ridesOngoing.containsKey(rideid) ? ridesOngoing.get(rideid): null;
                             if(rdeDetails == null || !rdeDetails.getStatus().equals(RideStatus.COMPLETED))   
                                System.out.println("INVALID_RIDE");
                            else
                            {
                                double amount = 50 ; // base fare
                                Rider rder = rdeDetails.getRider();
                                Driver dver = rdeDetails.getDriver();
                                double distanceTravelled = Math.sqrt(Math.pow(Math.abs(rder.getXCordinate()-rdeDetails.getDestXCord()),2)+(Math.pow(Math.abs(rder.getYCoordinate()- rdeDetails.getDestYCord()),2)));
                                distanceTravelled = Double.valueOf(df.format(distanceTravelled));
                                amount = amount+ distanceTravelled*6.5;
                                amount = Double.valueOf(df.format(amount));
                                amount = amount+ rdeDetails.getTime()*2;
                                amount = amount + (amount*0.2);
                                amount = Double.valueOf(df.format(amount));
                                System.out.println("BILL "+ rideid+" "+dver.getId()+ " "+amount);    
                            }   
                            break;                
                default: System.out.println("COMMAND_NOT_FOUND");
        };
           
    }
}
