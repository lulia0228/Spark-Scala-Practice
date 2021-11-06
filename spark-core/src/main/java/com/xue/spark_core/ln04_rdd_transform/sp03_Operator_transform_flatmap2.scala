package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp03_Operator_transform_flatmap2 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Any] = sc.makeRDD(
      List(List(1, 2), 3, "hello scala"), 3)

    //模式匹配
    val mapFlatRDD: RDD[Any] = rdd.flatMap(
      (data: Any) => {
        data match {
          case list: List[_] => list
          case s: String => s.split(" ")
          case dat => List(dat)
        }
      }
    )

    val arr: Array[Any] = mapFlatRDD.collect()
    arr.foreach(println)

    sc.stop()
  }

}
