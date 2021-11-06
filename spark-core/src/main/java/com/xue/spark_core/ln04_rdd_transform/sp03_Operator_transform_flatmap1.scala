package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object sp03_Operator_transform_flatmap1 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[String] = sc.makeRDD(
      List("hello spark", "hello scala"),2)

    val mapFlatRDD: RDD[String] = rdd.flatMap(
      (s: String) => s.split(" "))
    val arr: Array[String] = mapFlatRDD.collect()
    arr.foreach(println)

    sc.stop()
  }

}
