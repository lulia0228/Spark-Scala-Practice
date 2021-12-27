package com.xue.spark_core.trail

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object type_trans {
  def main(args: Array[String]): Unit = {
    //1. 建立和spark框架的链接
    //建立基本配置
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(config = sparkConf)

    val value: RDD[String] = sc.textFile("datas/file_1206_jiya.txt")
    val value1: RDD[Long] = value.map((item: String) => item.toLong)
    value1.foreach(println)
    val value3: RDD[Long] = value1.coalesce(50)

    val value2: RDD[Long] = sc.parallelize(List(1111926131L, 1112188298L, 1112268169L, 1112247936L))
    value2.foreach(println)
  }

}
