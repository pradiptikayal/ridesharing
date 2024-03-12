package com.ridesharing.Commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ridesharing.Model.Driver;
import com.ridesharing.Model.Rider;
import com.ridesharing.RideInfo.RideDetails;

public class StartRide implements Commander {

    String rideid;
    int nthDriver;
    String riderId;
    List<Rider> riderList;
    Map<Rider, List<Driver>> riderToNearestDrivers;
    Map<String,RideDetails> ridesOngoing;
    List<Driver> driverList;
    public StartRide(String rideid, int nthDriver, String riderId, List<Rider> riderList, Map<Rider, List<Driver>> riderToNearestDrivers, 
                Map<String,RideDetails> ridesOngoing, List<Driver> driverList)
    {
        this.rideid = rideid;
        this.nthDriver = nthDriver;
        this.riderId = riderId;
        this.riderList = riderList;
        this.riderToNearestDrivers = riderToNearestDrivers;
        this.ridesOngoing = ridesOngoing;
        this.driverList = driverList;
    }

    @Override
    public void performCommand() {
        Rider rdr = null;
        if(riderList.size() == 0 )
            System.out.println("INVAILD_RIDE");
        else
        {   
            try
            {   
                rdr = riderList.stream().filter(x -> x.id.equals(riderId)).collect(Collectors.toList()).get(0);
            }
            catch( Exception e )
            {
                System.out.println("INVAILD_RIDE");
                return;
            }
        }
        List<Driver> matchedDrvs = riderToNearestDrivers.get(rdr);
        if(matchedDrvs.size()<nthDriver || ridesOngoing.containsKey(rideid)) 
        {
            System.out.println("INVAILD_RIDE");
        }
        else
        {
                              
            Driver drv = null;
            try
            {
                drv = driverList.stream().filter( y -> y.id.equals(matchedDrvs.get(nthDriver-1).getId())).collect(Collectors.toList()).get(0);
                                    
            }
            catch(Exception e )
            {
                System.out.println("INVAILD_RIDE");
                return;
            }
            if(!drv.isAvailable())
                System.out.println("INVAILD_RIDE");
            else
            {
                drv.setAvailable(false);
                RideDetails rd = new RideDetails(rideid, rdr, drv);
                ridesOngoing.put(rideid, rd);
                System.out.println("RIDE_STARTED "+ rideid);
            }
        }
    }
    
}
