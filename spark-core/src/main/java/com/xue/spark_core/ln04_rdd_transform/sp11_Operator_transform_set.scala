package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp11_Operator_transform_set {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")
    val rdd1: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    val rdd2: RDD[Int] = sc.makeRDD(List(3,4,5,6),2)
    val rdd3: RDD[String] = sc.makeRDD(List("3","4","5","6"),2)

    //RDD转换算子  操作2个数据源
    //交集
    val val1: RDD[Int] = rdd1.intersection(rdd2)
    //val1.foreach(println)

    //并集
    val val2: RDD[Int] = rdd1.union(rdd2)
    //val2.foreach(println)

    //差集
    val val3: RDD[Int] = rdd1.subtract(rdd2)
    //val3.foreach(println)

    //拉链  zip 拉链的时候需要2个rdd分区数量一致 且分区数据量相同
    val val4: RDD[(Int, String)] = rdd1.zip(rdd3)
    println(val4.collect().mkString(","))

    sc.stop()

  }

}
