package laba3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkJob {
    public static void main(){
        SparkConf conf = new SparkConf().setAppName("lab5");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> AirportNames = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> AirportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");

        
    }
}
