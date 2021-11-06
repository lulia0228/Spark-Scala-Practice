package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp12_Operator_transform_sortBy {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(
             List(("china", 1), ("russia", 0), ("usa", 2)), 1)

    //RDD转换算子 sortBy指定规则排序
    val rdd1: RDD[(String, Int)] = rdd.sortBy(t => t._2, ascending = false)
    val arr: Array[(String, Int)] = rdd1.collect()
    arr.foreach(println)

    sc.stop()

  }

}
