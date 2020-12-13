package laba3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

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


    public static void main(){
        SparkConf conf = new SparkConf().setAppName("lab5");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportNames = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> airportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, String> airportNameData =
                airportNames
                .filter(str -> str.contains("Code")).mapToPair(value ->{
                    String[] table = value.split(NAMEDILIMETR);
                    Integer airportID = Integer.valueOf(table[DESTAIRPORTIDFORNAMES]
                            .replaceAll("\"", ""));
                    return  new Tuple2<>(airportID, table[NAMEAIRPORT]);
                });
        

    }
}
