package laba3;

import java.io.Serializable;

public class FlightSerializablCount implements Serializable {
    public FlightSerializablCount() {}
    private float maxArrDelay;
    private int delayCount;
    private int flightCount;
    private int cancelledCount;

    public FlightSerializablCount(int cancelledCount, int delayCount, int flightCount, float maxArrDelay){
        this.cancelledCount = cancelledCount;
        this.delayCount = delayCount;
        this.flightCount = flightCount;
        this.maxArrDelay = maxArrDelay;
    }
    public float getMaxArrDelay(){
        return maxArrDelay;
    }
    public int getDelayCount(){
        return delayCount;
    }
    public int getFlightCount(){
        return flightCount;
    }
    public int getCancelledCount(){
        return cancelledCount;
    }

    public static FlightSerializablCount addValue(FlightSerializablCount a, float maxArrDelay, bollean deayed, boolean cencelled){
        
    }
}
