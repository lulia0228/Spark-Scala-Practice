package com.xue.spark_core.ln06_accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object sp02_acc_intro {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(4,2,3,1),2)
    //spark自带的累加器，创建累加器
    val sumACC: LongAccumulator = sc.longAccumulator(name = "sum")
    val mapRDD: RDD[Unit] = rdd.map((num: Int) => {
      sumACC.add(num)
    })
    //使用转换算子调用累加器的时候，必须使用行动算子采集，才会真正改变变量值
    mapRDD.collect()
    println(sumACC.value)

  }

}
