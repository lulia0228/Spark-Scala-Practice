package com.xue.spark_core.ln05_rdd_action

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * 行动算子，触发作业的执行
 */

object sp01_Operator_action_collect {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    // 行动算子 collect 运行作业
    val arr: Array[Int] = rdd.collect()

    sc.stop()
  }

}
