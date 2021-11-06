package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp16_Operator_transform_aggregateByKey01 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a",10), ("a",6),("a",3),("a",2),("a",1),("a",7)), 2)

    // RDD转换算子 aggregateByKey 处理键值对类型数据
    // 作用：按不同的规则将分区内和分区间执行分开计算
    // 使用：存在函数柯里化使用
    //    有2个参数列表，第二个参数列表有2个函数参数
    //    第一个参数列表有1个参数，是一个分区内可以用于第一个两两计算的初始值
    //    第二个参数列表2个函数参数，分别表示分区内的操作和分区间的操作

    //val rdd1: RDD[(String, Int)] = rdd.aggregateByKey(0)(math.max, _ + _)
    val rdd1: RDD[(String, Int)] = rdd.aggregateByKey(0)(
      (x,y)=>math.max(x,y), (x,y)=>x+y )
    val arr: Array[(String, Int)] = rdd1.collect()
    println(arr.mkString("Array(", ", ", ")"))

    sc.stop()

  }

}
