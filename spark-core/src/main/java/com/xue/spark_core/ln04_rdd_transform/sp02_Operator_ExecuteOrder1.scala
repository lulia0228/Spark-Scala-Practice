package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * 1. 使用map时候 rdd分区执行逻辑
 *  rdd一个分区内部的数据是一个一个的执行；
 *  只有前面一个数据的所有逻辑执行完毕，才会执行下一个数据的全部逻辑
 */

object sp02_Operator_ExecuteOrder1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),1)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    val rdd1: RDD[Int] = rdd.map(
      (num: Int) =>{
        println("<<<<<<<"+num)
        num
      }
    )

    val rdd2: RDD[Int] = rdd1.map(
      (num: Int) =>{
        println("########"+num)
        num
      }
    )

    val arr: Array[Int] = rdd2.collect()

    sc.stop()

  }

}
