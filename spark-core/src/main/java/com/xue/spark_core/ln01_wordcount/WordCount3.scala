package com.xue.spark_core.ln01_wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Spark 对map reduce操作做了优化封装；有更好使用的api完成复杂多步操作
 */

object WordCount3 {
  def main(args: Array[String]): Unit = {

    //1. 建立和spark框架的链接
    //建立基本配置
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(config = sparkConf)

    //2. 执行业务操作
    val lines: RDD[String] = sc.textFile(path = "datas")

    //写完语句输入.var  自动补全变量
    //匿名函数简化写法
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordToOne: RDD[(String, Int)] = words.map(word => (word, 1))

    //spark的reduceByKey可以相同的key数据对value聚合
    // reduceByKey((x,y)=>x+y)
    // reduceByKey((x,y)=>{x+y})
    val wordToCount: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)

    //转换结果采集到控制台后打印
    val arr: Array[(String, Int)] = wordToCount.collect()
    arr.foreach(println)
    println("----------" * 10)

    //一行写完上面的操作
    val arr1: Array[(String, Int)] = sc.textFile("datas")
      .flatMap(_.split(" "))
      .map((_, 1)).reduceByKey(_ + _)
      .collect
    arr1.foreach(println)

    //3. 关闭连接
    sc.stop()
  }

}
