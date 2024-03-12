package com.ridesharing.Commands;

import java.util.Map;

import com.ridesharing.RideInfo.RideDetails;
import com.ridesharing.RideInfo.RideStatus;

public class StopRide implements Commander {

    String rideid;
    Map<String,RideDetails> ridesOngoing;
    int xCord;
    int yCord;
    int time;

    public StopRide(String rideid, Map<String,RideDetails> ridesOngoing, int xCord, int yCord, int time)
    {
        this.rideid = rideid;
        this.ridesOngoing = ridesOngoing;
        this.xCord = xCord;
        this.yCord = yCord;
        this.time = time;
    }


    @Override
    public void performCommand() {
        RideDetails rdetail = ridesOngoing.containsKey(rideid) ? ridesOngoing.get(rideid) : null;
        if(rdetail == null || rdetail.getStatus().equals(RideStatus.COMPLETED))
            System.out.println("INVALID_RIDE");
        else
        { 
            rdetail.setDestXCord(xCord);
            rdetail.setDestYCord(yCord);
            rdetail.setTime(time);
            rdetail.setStatus(RideStatus.COMPLETED);  
            ridesOngoing.put(rideid, rdetail);
            System.out.println("RIDE_STOPPED "+ rideid);
        }
    }
    
}
