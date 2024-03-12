package com.ridesharing.Commands;

import java.text.DecimalFormat;
import java.util.Map;

import com.ridesharing.Model.Driver;
import com.ridesharing.Model.Rider;
import com.ridesharing.RideInfo.RideDetails;
import com.ridesharing.RideInfo.RideStatus;

public class GenerateBill implements Commander {

    String rideid;
    Map<String,RideDetails> ridesOngoing;
    public GenerateBill(String rideid, Map<String,RideDetails> ridesOngoing)
    {
        this.rideid = rideid;
        this.ridesOngoing = ridesOngoing;
    }
    @Override
    public void performCommand() {
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
    }
    
}
