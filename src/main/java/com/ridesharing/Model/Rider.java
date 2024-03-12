package com.ridesharing.Model;
import lombok.Getter;
import lombok.Setter;

public class Rider {
    
    @Getter @Setter public String id;
    @Getter @Setter int xCordinate;
    @Getter @Setter int yCoordinate;

    public Rider( String id, int x, int y)
    {
        this.id = id;
        this.xCordinate = x;
        this.yCoordinate = y;
    }
}
