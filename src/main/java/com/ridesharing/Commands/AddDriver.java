package com.ridesharing.Commands;

import java.util.List;

import com.ridesharing.Model.Driver;

public class AddDriver implements Commander {

    String driverid;
    int xCord;
    int yCord;
    List<Driver> driverList;
    public AddDriver( String driverid, int xCord, int yCord, List<Driver> driverList)
    {
        this.driverid = driverid;
        this.xCord = xCord;
        this.yCord = yCord;
        this.driverList = driverList;
    }
    @Override
    public void performCommand() {
        Driver d = new Driver(driverid, xCord, yCord);
        this.driverList.add(d);
    }
    
}
