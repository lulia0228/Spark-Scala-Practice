package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp18_Operator_transform_combineByKey {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a",10), ("b",6),("a",3),("a",2),("b",1),("a",7)), 2)

    // RDD转换算子 combineByKey 处理键值对类型数据
    // 作用：按不同的规则将分区内和分区间执行分开计算
    // 使用：
    //    第一个参数，将相同key的第一个数据的v做结构转换（和aggregateByKey的区别）
    //    第二个参数，分区内的两两操作规则
    //    第二个参数，分区间的两两操作规则

    //任务:取出不同分区中不同数据的平均值
    val rdd1: RDD[(String, (Int, Int))] = rdd.combineByKey(
      v=>(v,1),
      (t:(Int,Int), v) => (t._1 + v, t._2 + 1),
      (t1:(Int,Int), t2:(Int,Int)) => (t1._1 + t2._1, t1._2 + t2._2))

    val arr: RDD[(String, Float)] = rdd1.mapValues {
      case (total, cnt) => total / cnt.toFloat
    }
    arr.foreach(println)

    sc.stop()

  }

}
