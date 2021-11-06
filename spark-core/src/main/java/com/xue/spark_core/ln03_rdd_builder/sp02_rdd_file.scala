package com.xue.spark_core.ln03_rdd_builder

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object sp02_rdd_file {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //2 从文件中创建RDD
    //可以是目录，可以是某个具体文件

    val rdd: RDD[String] = sc.textFile("datas")
    val arr: Array[String] = rdd.collect()

    //val rdd: RDD[(String, String)] = sc.wholeTextFiles("datas")
    //val arr: Array[(String, String)] = rdd.collect()

    arr.foreach(println)

    sc.stop()
  }

}
