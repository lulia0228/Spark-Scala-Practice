package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp20_Operator_transform_leftOuterJoin {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)), 1)
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("b", 10), ("c", 20),("d", 30)), 1)

    // RDD转换算子 操作2个数据源
    // leftOuterJoin将2个数据源相同key数据的值连接，没有相同key的数据以左表为主也返回
    val tp1: RDD[(String, (Int, Option[Int]))] = rdd1.leftOuterJoin(rdd2)
    tp1.collect().foreach(println)
    println("============================================")
    val tp2: RDD[(String, (Option[Int], Int))] = rdd1.rightOuterJoin(rdd2)
    tp2.collect().foreach(println)
    sc.stop()

  }

}
