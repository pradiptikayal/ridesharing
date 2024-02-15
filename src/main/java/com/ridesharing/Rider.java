package com.ridesharing;
import lombok.Getter;
import lombok.Setter;

public class Rider {
    
    @Getter @Setter String id;
    @Getter @Setter int xCordinate;
    @Getter @Setter int yCoordinate;

    Rider( String id, int x, int y)
    {
        this.id = id;
        this.xCordinate = x;
        this.yCoordinate = y;
    }
}
