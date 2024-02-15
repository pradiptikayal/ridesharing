package com.ridesharing;
import lombok.Getter;
import lombok.Setter;

public class Driver {
    @Getter @Setter String id;
    @Getter @Setter int xCordinate;
    @Getter @Setter int yCordinate;
    @Getter @Setter boolean isAvailable = true;
    Driver( String id, int x, int y)
    {
        this.id = id;
        this.xCordinate = x;
        this.yCordinate = y;
    }
}
