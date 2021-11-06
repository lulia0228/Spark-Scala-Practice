package com.xue.spark_core.ln03_rdd_builder

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object sp01_rdd_partition {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //1 从内存中创建RDD
    //numSlices可指定分区数，不传是默认值
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    rdd.saveAsTextFile("outputs")

    val arr: Array[Int] = rdd.collect()
    arr.foreach(println)

    sc.stop()
  }

}
