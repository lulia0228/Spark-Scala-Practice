package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp17_Operator_transform_foldByKey {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a",10), ("b",6),("a",3),("b",2),("a",1),("b",7)), 2)

    // RDD转换算子 foldByKey 处理键值对类型数据
    // 作用：如果分区内核分区间计算规则一致，使用foldByKey（和aggregateByKey的区别）
    // 使用：存在函数柯里化使用
    //    第一个参数，是一个分区内可以用于第一个两两计算的初始值
    //    第二个参数，分区内和分区间的操作规则

    //val rdd1: RDD[(String, Int)] = rdd.reduceByKey(_+_)
    //val rdd1: RDD[(String, Int)] = rdd.aggregateByKey(0)(_+_, _+_)
    val rdd1: RDD[(String, Int)] = rdd.foldByKey(0)((x,y)=>x+y)
    val arr: Array[(String, Int)] = rdd1.collect()
    println(arr.mkString("Array(", ", ", ")"))

    sc.stop()

  }

}
