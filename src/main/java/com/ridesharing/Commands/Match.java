package com.ridesharing.Commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ridesharing.Model.Driver;
import com.ridesharing.Model.Rider;
import com.ridesharing.Utility.Matcher;

public class Match implements Commander{

    String riderid;
    List<Driver> driverList;
    List<Rider> riderList;
    Map<Rider, List<Driver>> riderToNearestDrivers;

    public Match(String riderid, List<Driver> driverList, List<Rider> riderList, Map<Rider, List<Driver>> riderToNearestDrivers)
    {
        this.riderid = riderid;
        this.driverList = driverList;
        this.riderList = riderList;
        this.riderToNearestDrivers = riderToNearestDrivers;
    }

    @Override
    public void performCommand() {
        Rider rider = null;
        try
        {
            rider= riderList.stream().filter(a -> a.id.equals(riderid)).collect(Collectors.toList()).get(0);
        }
        catch(Exception e )
        {
            System.out.println("NO_DRIVERS_AVAILABLE"); 
            return;
        }
        Matcher matcher = new Matcher(this.driverList);
        List<Driver> driversMatched = matcher.getMatchedDrivers(rider);
        if(driversMatched.size()> 0)
        {
            System.out.print("DRIVERS_MATCHED ");
            int k =1;  
            for(Driver driver : driversMatched) {
                if( k > 5)
                    break;
                System.out.print(driver.id+" ");
                k++;    
            }
            riderToNearestDrivers.put(rider, driversMatched);
            System.out.println();
        }
        else
            System.out.println("NO_DRIVERS_AVAILABLE"); 
    }
    
}
