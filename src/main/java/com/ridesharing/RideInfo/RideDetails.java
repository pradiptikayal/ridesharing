package com.ridesharing.RideInfo;
import com.ridesharing.Model.Driver;
import com.ridesharing.Model.Rider;

import lombok.Getter;
import lombok.Setter;

public class RideDetails {
    
    @Getter @Setter String rideId;
    @Getter @Setter Rider rider;
    @Getter @Setter Driver driver;
    @Getter @Setter RideStatus status = RideStatus.STARTED;
    @Getter @Setter int destXCord;
    @Getter @Setter int destYCord;
    @Getter @Setter int time;

    public RideDetails( String rideId, Rider r, Driver d)
    {
        this.rideId = rideId;
        this.rider = r;
        this.driver = d;
    }

}
