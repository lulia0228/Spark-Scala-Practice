package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp08_Operator_transform_distinct {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,2,3,3,4,5,5),2)

    //RDD转换算子 distinct数据去重
    val rdd1: RDD[Int] = rdd.distinct()
    val arr: Array[Int] = rdd1.collect()
    arr.foreach(println)

    sc.stop()

  }

}
