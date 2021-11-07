package com.xue.spark_core.ln05_rdd_action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 行动算子，触发作业的执行
 */

object sp03_Operator_action_aggregate {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(4,2,1,3),2)

    // 1 行动算子 aggregate
    // 初始值会参与分区内计算，且会参与分区间计算；
    val res: Int = rdd.aggregate(10)(_+_, _ + _)
    println(res) //10 + （10+4+2）+ （10+1+3）

    // 2 行动算子 fold当分区内和分区间规则一致，用fold
    val res1: Int = rdd.fold(10)(_+_)
    println(res1)

    sc.stop()
  }

}
