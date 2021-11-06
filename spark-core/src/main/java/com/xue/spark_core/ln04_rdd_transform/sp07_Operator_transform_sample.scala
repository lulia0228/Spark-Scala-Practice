package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp07_Operator_transform_sample {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(1.to(10),2)

    //RDD转换算子
    val rdd1: RDD[Int] = rdd.sample(withReplacement=true, fraction=0.4, seed=1)
    val arr: Array[Int] = rdd1.collect()
    arr.foreach(println)

    sc.stop()

  }

}
