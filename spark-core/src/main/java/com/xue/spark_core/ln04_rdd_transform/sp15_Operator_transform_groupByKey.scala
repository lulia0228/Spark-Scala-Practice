package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sp15_Operator_transform_groupByKey {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")

    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("china", 1), ("china", 5), ("usa", 2), ("uk",3), ("uk",1),("china", 2)), 2)

    //RDD转换算子 groupByKey相同key的数据进行分组  分组后键为key；值为原来值的迭代器
    //groupByKey和reduceByKey都会有shuffle操作，但是groupByKey只有分组的功能
    val rdd1: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    val tp: Array[(String, Iterable[Int])] = rdd1.collect()
    tp.foreach(println)

    sc.stop()

  }

}
