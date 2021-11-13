package com.xue.spark_core.ln06_accumulator

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator

object sp01_acc_intro {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(4,2,3,1),2)
    //spark自带的累加器
    val sumACC: LongAccumulator = sc.longAccumulator(name = "sum")
    rdd.foreach((num: Int) => sumACC.add(num))
    println(sumACC.value)

  }

}
