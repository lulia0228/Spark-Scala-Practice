package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object sp13_Operator_transform_partitionBy {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("china", 1), ("russia", 0), ("usa", 2), ("uk",3)), 2)

    //RDD转换算子 partitionBy只能处理键值对类型数据  根据指定的分区规则，重新对数据进行分区
    //此方法由PairRDD类提供，但是这里是RDD对象调用，原因是自动隐式转换
    val rdd1: RDD[(String, Int)] = rdd.partitionBy(new HashPartitioner(4))
    rdd1.saveAsTextFile("./outputs")
    println(rdd1.collect().mkString(","))

    sc.stop()

  }

}
