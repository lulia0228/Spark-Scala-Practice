package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object sp01_Operator_transform_intro {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    //RDD转换算子
    //val rdd1: RDD[Int] = rdd.map((num:Int)=>{num*2})
    //val rdd1: RDD[Int] = rdd.map(num=>num*2)

    val rdd1: RDD[Int] = rdd.map(_ * 2)
    val arr: Array[Int] = rdd1.collect()
    arr.foreach(println)

    sc.stop()

  }

}
