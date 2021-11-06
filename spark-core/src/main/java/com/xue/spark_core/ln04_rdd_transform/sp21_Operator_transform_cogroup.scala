package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp21_Operator_transform_cogroup {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,2,3,3,4,5,5),5)

    //RDD转换算子 coalesce缩减分区 用于大数据过滤后，提高小数据执行效率
    val rdd1: RDD[Int] = rdd.coalesce(2)
    val arr: Array[Int] = rdd1.collect()
    arr.foreach(println)

    sc.stop()

  }

}
