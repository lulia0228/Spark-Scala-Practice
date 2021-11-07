package com.xue.spark_core.ln05_rdd_action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 行动算子，触发作业的执行
 */

object sp04_Operator_action_countBy_ {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd1: RDD[Int] = sc.makeRDD(List(4,2,1,3,2,3,3),2)
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a", 10), ("b", 1), ("a", 4), ("b", 6), ("a", 2)), 2)

    // 1 行动算子 countByValue
    // 是对整个数据类型做统计，无论是什么类型
    val mp1: collection.Map[Int, Long] = rdd1.countByValue()
    println(mp1) //Map(4 -> 1, 2 -> 2, 1 -> 1, 3 -> 3)

    // 2 行动算子 countByKey作用于键值对数据的key
    val mp2: collection.Map[String, Long] = rdd2.countByKey()
    println(mp2) //Map(b -> 2, a -> 3)
    val mp3: collection.Map[(String, Int), Long] = rdd2.countByValue()
    println(mp3)

    sc.stop()
  }

}
