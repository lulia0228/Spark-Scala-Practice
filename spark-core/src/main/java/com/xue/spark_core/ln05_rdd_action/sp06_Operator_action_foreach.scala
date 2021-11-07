package com.xue.spark_core.ln05_rdd_action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 行动算子，触发作业的执行
 */

object sp06_Operator_action_foreach {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(4,2,3,1),2)

    // 1 行动算子 foreach
    //先用collect采集再打印和直接foreach打印顺序不同，因为collect前面提到会按照分区顺序采集数据
    rdd.collect().foreach(println) //方法是driver端内存集合的遍历方法，是scala的方法
    println("****")
    rdd.foreach(println)//方法是Executor端执行的，是RDD的方法，分布式执行

    sc.stop()
  }

}
