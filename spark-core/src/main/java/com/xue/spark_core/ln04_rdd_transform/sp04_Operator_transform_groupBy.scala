package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * groupBy分组
 * groupBy会将原始分区数据打乱，会重新组合(shuffle),分区数量不变
 */

object sp04_Operator_transform_groupBy {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)

    //groupBy按照key分组
    val groupRDD: RDD[(Int, Iterable[Int])] = rdd.groupBy(
      (num: Int) => num % 2
    )
    groupRDD.foreach(println)

    sc.stop()
  }

}

//(0,CompactBuffer(2, 4))
//(1,CompactBuffer(1, 3))