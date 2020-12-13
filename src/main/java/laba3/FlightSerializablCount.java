package laba3;

import java.io.Serializable;

public class FlightSerializablCount implements Serializable {
    public FlightSerializablCount() {}
    
    private float maxArrDelay;
    private int delayCount;
    private int flightCount;
    private int cancelledCount;

    public FlightSerializablCount(int flightCount, int delayCount, float maxArrDelay, int cancelledCount){
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

    public static FlightSerializablCount addValue(FlightSerializablCount a, float maxArrDelay, boolean delayed, boolean cancelled){
        return new FlightSerializablCount(a.getFlightCount() + 1,
                delayed ? a.getDelayCount() + 1 : a.getDelayCount(),
                Math.max(a.getMaxArrDelay(), maxArrDelay), //??
                cancelled ? a.getCancelledCount() + 1 : a.getCancelledCount());
    }

    public static FlightSerializablCount add(FlightSerializablCount a, FlightSerializablCount b){
        return new FlightSerializablCount(a.getFlightCount() + b.getFlightCount(),
                a.getDelayCount() + b.getDelayCount(),
                Math.max(a.getMaxArrDelay(), b.getMaxArrDelay()), //??
                a.getCancelledCount() + b.getCancelledCount());
    }

    public static String toOutString(FlightSerializablCount a){
        float delaysPercend = (float) a.getDelayCount() / a.getFlightCount() * 100;
        float cancelledPercend = (float) a.getCancelledCount() / a.getFlightCount() * 100;
        return "INF : {MAX DELAY: " + a.getMaxArrDelay() +
                "; FLIGHTS : " + a.getFlightCount() +
                "; DELAYS : " + a.getDelayCount() +
                "; CANCELLED : " + a.getCancelledCount() +
                "}; % DELAYS = " + delaysPercend +
                " % CANCELLED = " + cancelledPercend;
    }
}
