package com.xue.spark_core.ln05_rdd_action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 行动算子，触发作业的执行
 */

object sp02_Operator_action_reduce {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(4,2,1,3),2)

    // 1 行动算子 reduce
    val res: Int = rdd.reduce(_ + _)
    println(res)

    // 2 行动算子 collect 将不同分区的数据按分区顺序采集到driver端
    val arr: Array[Int] = rdd.collect()
    println(arr.mkString(":"))

    // 3 行动算子 count 返回rdd中数据数量
    val length: Long = rdd.count()
    println(length)

    // 4 行动算子 first 返回rdd中第一个数据
    val fs: Int = rdd.first()
    println(fs)

    // 5 行动算子 take 返回rdd中前n个数据
    val arr1: Array[Int] = rdd.take(3)
    println(arr1.mkString(","))

    // 6 行动算子 takeOrdered 返回rdd中排序后的前n个数据
    val arr2: Array[Int] = rdd.takeOrdered(3)
    println(arr2.mkString(";"))

    sc.stop()
  }

}
