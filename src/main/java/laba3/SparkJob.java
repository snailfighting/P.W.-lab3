package laba3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkJob {
    private static final String NAMEDILIMETR = "\",";
    private static final String DELAYDElIMETR = ",";
    private static final int NAMEAIRPORT = 1;
    private static final int ARRDELAY = 17;
    private static final String NULLSRING = "";
    private static final float ZERO = 0.0f;

    public static void main(){
        SparkConf conf = new SparkConf().setAppName("lab5");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> AirportNames = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> AirportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, Integer>, 

    }
}
