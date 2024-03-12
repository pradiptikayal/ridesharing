package com.ridesharing.Utility;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ridesharing.Model.Driver;
import com.ridesharing.Model.Rider;

import java.util.HashMap;

public class Matcher {
    
    List<Driver> drivers;
    public Matcher( List<Driver> drivers )
    {
        this.drivers = drivers;
    }

    public List<Driver> getMatchedDrivers( Rider r )
    {
        HashMap<Driver, Double> toReturn = new HashMap<>();
        // get available drivers within 5 kms distance
        // compare the distance and return the result
        for(Driver d: drivers)
        {
            int driverXCord = d.getXCordinate();
            int driverYCord = d.getYCordinate();
            int riderXCord = r.getXCordinate();
            int riderYCord = r.getYCoordinate();
            double distance = Math.sqrt(Math.pow(Math.abs(riderXCord - driverXCord),2)+ Math.pow(Math.abs(riderYCord-driverYCord),2));
            if( Math.floor(distance) <= 5)
                toReturn.put(d,distance);
        }
        List<Driver> returnList = getSortedDriverBasedOnDistance(toReturn);
        return returnList;
    }

    private List<Driver> getSortedDriverBasedOnDistance( HashMap<Driver, Double> mapOfDriverToDistance )
    {
       List<Map.Entry<Driver, Double>> sortedMap =  mapOfDriverToDistance.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
       List<Driver> driversInSortedOrder = sortedMap.stream().map(Map.Entry::getKey).collect(Collectors.toList());
       return driversInSortedOrder;   
    }
}
