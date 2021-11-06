package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp14_Operator_transform_reduceByKey {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")

    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("china", 1), ("china", 5), ("usa", 2), ("uk",3), ("uk",1)), 2)

    //RDD转换算子 reduceByKey相同key的数据进行value的聚合   每次是2个数据的聚合
    //reduceByKey可以在shuffle之前进行分区内预聚合，包含分组和聚合功能。
    val rdd1: RDD[(String, Int)] = rdd.reduceByKey(_ + _)

    val tp: Array[(String, Int)] = rdd1.collect()
    tp.foreach(println)

    sc.stop()

  }

}
