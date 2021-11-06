package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * glob可以将rdd一个分区的数据形成一个数组，
 * 形成新的RDD类型RDD[Array[T]]
 */

object sp05_Operator_transform_glob {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)

    //把一个分区形成数组
    val glomRDD: RDD[Array[Int]] = rdd.glom()

    //每个分区的最大值
    val maxRDD: RDD[Int] = glomRDD.map(
      arr => arr.max
    )

    val sum: Int = maxRDD.collect().sum
    println(sum)

    sc.stop()
  }

}
