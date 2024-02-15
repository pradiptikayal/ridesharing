package com.ridesharing;

import java.util.ArrayList;
import java.util.List;

public class Matcher {
    
    List<Driver> drivers;
    Matcher( List<Driver> drivers )
    {
        this.drivers = drivers;
    }

    List<Driver> getMatchedDrivers( Rider r )
    {
        List<Driver> toReturn = new ArrayList<>();
        // get available drivers within 5 kms distance
        //TODO:: implement comparator to comparare the distance and return the result
        for(Driver d: drivers)
        {
            int driverXCord = d.getXCordinate();
            int driverYCord = d.getYCordinate();
            int riderXCord = r.getXCordinate();
            int riderYCord = r.getYCoordinate();
            double distance = Math.sqrt(Math.pow(Math.abs(riderXCord - driverXCord),2)+ Math.pow(Math.abs(riderYCord-driverYCord),2));
            if( Math.floor(distance) <= 5)
                toReturn.add(d);
        }
        return toReturn;
    }
}
