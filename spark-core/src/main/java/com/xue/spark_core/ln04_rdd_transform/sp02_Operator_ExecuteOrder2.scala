package com.xue.spark_core.ln04_rdd_transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 2. 使用mapPartitions时候 rdd分区执行逻辑
 *  以分区为单位进行数据转换，但是分区内数据会被加载到内存，因为引用的存在不会被释放
 *  所以数据量大这种方式虽然速度快，但是可能内存溢出
 */

object sp02_Operator_ExecuteOrder2 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),1)
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    val rdd1: RDD[Int] = rdd.mapPartitions(
      (iter: Iterator[Int]) =>{
        println("<<<<<<<") //只打印两次
        //iter.map(_*2)
        iter
      }
    )

    val rdd2: RDD[Int] = rdd1.mapPartitions(
      (iter: Iterator[Int]) =>{
        println("#########") //只打印两次
        //iter.map(_*2)
        iter
      }
    )

    val arr: Array[Int] = rdd2.collect()
    arr.foreach(println)

    sc.stop()

  }

}
