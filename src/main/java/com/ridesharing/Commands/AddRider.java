package com.ridesharing.Commands;

import java.util.List;

import com.ridesharing.Model.Rider;

public class AddRider implements Commander {

    String riderid;
    int xCord;
    int yCord;
    List<Rider> riderList;
    public AddRider(String riderid, int xCord, int yCord, List<Rider> riderList)
    {
        this.riderid = riderid;
        this.xCord = xCord;
        this.yCord = yCord;
        this.riderList = riderList;
    }
    @Override
    public void performCommand() {
        Rider r = new Rider( riderid, xCord, yCord);
        this.riderList.add(r);
    }
    
}
