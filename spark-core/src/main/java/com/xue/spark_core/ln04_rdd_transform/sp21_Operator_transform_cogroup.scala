package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp21_Operator_transform_cogroup {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2)), 1)
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("b", 10), ("c", 20),("c", 30)), 2)

    // RDD转换算子  操作2个数据源
    // cogroup = connect + group
    val tp: RDD[(String, (Iterable[Int], Iterable[Int]))] = rdd1.cogroup(rdd2)
    tp.collect().foreach(println)

    sc.stop()

  }

}
