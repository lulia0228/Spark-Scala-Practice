package com.xue.spark_core.ln05_rdd_action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 行动算子，触发作业的执行
 */

object sp05_Operator_action_save {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a", 10), ("b", 1), ("a", 4), ("b", 2)), 2)

    // 1 行动算子  save
    rdd.saveAsTextFile("outputs")
    rdd.saveAsObjectFile("outputs1")
    //数据必须是键值对类型
    rdd.saveAsSequenceFile("outputs2")

    sc.stop()

  }

}
