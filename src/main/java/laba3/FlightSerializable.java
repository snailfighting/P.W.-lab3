package laba3;

import java.io.Serializable;

public class FlightSerializable implements Serializable {
    public FlightSerializable(){}

    private float arrDelay;
    private float cancelled;

    private int airportID;
    private int originalAirportID;

    public FlightSerializable(int airportID, int originalAirportID, float arrDelay, float cancelled){
        this.airportID = airportID;
        this.arrDelay = arrDelay;
        this.cancelled = cancelled;
        this.originalAirportID = originalAirportID;
    }
    public float getArrDelay(){
        
    }
}
