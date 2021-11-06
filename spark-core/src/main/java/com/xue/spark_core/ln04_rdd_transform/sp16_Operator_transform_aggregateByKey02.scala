package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp16_Operator_transform_aggregateByKey02 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a",10), ("b",6),("a",3),("a",2),("b",1),("a",7)), 2)

    // RDD转换算子 aggregateByKey 处理键值对类型数据
    // 作用：按不同的规则将分区内和分区间执行分开计算
    // 使用：存在函数柯里化使用
    //    有2个参数列表，第二个参数列表有2个函数参数
    //    第一个参数列表有1个参数，是一个分区内可以用于第一个两两计算的初始值
    //    第二个参数列表2个函数参数，分别表示分区内的操作和分区间的操作

    //任务:取出不同分区中不同数据的平均值
    val rdd1: RDD[(String, (Int, Int))] = rdd.aggregateByKey((0, 0))(
      (t, v) => (t._1 + v, t._2 + 1), //t初始即前面指定的(0,0)，两两操作，v即分区内每个数据的value
      (t1, t2) => (t1._1 + t2._1, t1._2 + t2._2)) //(t1,t2)即分区内计算得到的t,然后分区间做合并计算

    val arr: RDD[(String, Float)] = rdd1.mapValues {
      case (total, cnt) => total / cnt.toFloat
    }
    arr.foreach(println)

    sc.stop()

  }

}
