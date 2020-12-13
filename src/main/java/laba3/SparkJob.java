package laba3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;
import scala.reflect.internal.InfoTransformers;

import java.util.Map;

public class SparkJob {
    private static final String NAMEDILIMETR = "\",";
    private static final String DELAYDElIMETR = ",";
    private static final int NAMEAIRPORT = 1;
    private static final int ARRDELAY = 17;
    private static final int CANCELLED = 19;

    private static final String NULLSRING = "";
    private static final float ZERO = 0.0f;

    private static final int DESTAIRPORTIDFORNAMES= 0;
    private static final int DESTAIRPORTIDFORDELAYS = 14;

    private static final int ORIGINALAIRPORTID = 11;


    private static float checkNull(String current){
        if (current.equals(NULLSRING)){
            return ZERO;
        }else{
            return Float.parseFloat(current);
        }
    }

    public static void main(){
        SparkConf conf = new SparkConf().setAppName("lab5");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportNames = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> airportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, String> airportNameData =
                airportNames
                .filter(str -> !str.contains("Code")).mapToPair(value ->{
                    String[] table = value.split(NAMEDILIMETR);
                    Integer airportID = Integer.valueOf(table[DESTAIRPORTIDFORNAMES]
                            .replaceAll("\"", ""));
                    return  new Tuple2<>(airportID, table[NAMEAIRPORT]);
                });
        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> airportDelaysData =
                airportDelays.
                        filter(str -> !str.contains("YEAR")).
                        mapToPair(value -> {
                            String[] table = value.split(DELAYDElIMETR);
                            int airportID = Integer.parseInt(table[DESTAIRPORTIDFORDELAYS]);
                            int originalAirportID = Integer.parseInt(table[ORIGINALAIRPORTID]);
                            float arrDelay = checkNull(table[ARRDELAY]);
                            float cancelled = Float.parseFloat(table[CANCELLED]);
                            return new Tuple2<>(new Tuple2<>(originalAirportID, airportID),
                                    new FlightSerializable(airportID, originalAirportID, arrDelay, cancelled));
                        });
        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializablCount> flightSerCounts =
                airportDelaysData.combineByKey(p -> new FlightSerializablCount(1,
                                p.getArrDelay() > ZERO ? 1 : 0,
                                p.getArrDelay(),
                                p.getCancelled() == ZERO ? 0 : 1),
                                (flightSerCount,p) -> FlightSerializablCount.addValue(flightSerCount,
                                     p.getArrDelay(),
                                        p.getArrDelay() != ZERO,
                                p.getCancelled() != ZERO),
                        FlightSerializablCount :: add);

        JavaPairRDD<Tuple2<Integer, Integer>, String> flSerCountStr = flightSerCounts
                .mapToPair(value -> {
                    value._2();
                    return new Tuple2<>(value._1(),FlightSerializablCount.toOutString(value._2()));
                });

        final Broadcast<Map<Integer, String>> broadcast = sc.broadcast(airportNameData.collectAsMap());

        JavaRDD<String> out = flSerCountStr.map(value ->{
            Map<Integer, String> airport_Names = broadcast.value();

            String startAirportsName = airport_Names.get(value._1()._1());
            String finishAirportsName = airport_Names.get(value._1()._2());

            return startAirportsName + "-->" + finishAirportsName + ">>>>>" + value._2();

        });

        out.saveAsTextFile("hdfs://localhost:9000/user/milena/output");
    }
}
