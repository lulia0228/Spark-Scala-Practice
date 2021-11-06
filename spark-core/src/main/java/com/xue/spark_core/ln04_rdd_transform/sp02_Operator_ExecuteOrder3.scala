package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 3. 使用mapPartitionsWithIndex
 *    可以对不同分区执行不同操作；可额外获得分区编号信息
 */

object sp02_Operator_ExecuteOrder3 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),1)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    val rdd1: RDD[Int] = rdd.mapPartitionsWithIndex(
      (index: Int, iter: Iterator[Int]) =>{
        if (index==1){
          iter
        }else{
          Nil.iterator
        }
      }
    )

    val arr: Array[Int] = rdd1.collect()
    arr.foreach(println)

    sc.stop()

  }

}
