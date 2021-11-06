package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp19_Operator_transform_join {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)), 1)
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("b", 10), ("c", 20), ("d", 30)), 1)

    // RDD转换算子 操作2个数据源
    // join将2个数据源相同key数据的值连接，没有相同key的数据不会出现在最终结果中
    val tp: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
    tp.collect().foreach(println)
    sc.stop()

  }

}
