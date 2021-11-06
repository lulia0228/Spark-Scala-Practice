package com.xue.spark_core.ln03_rdd_builder

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp01_rdd_memory {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //1 从内存中创建RDD
    val seq: Seq[Int] = Seq[Int](1,2,3,4)
    //val rdd: RDD[Int] = sc.parallelize(seq)
    val rdd: RDD[Int] = sc.makeRDD(seq)

    val arr: Array[Int] = rdd.collect()
    arr.foreach(println)

    sc.stop()

  }

}
