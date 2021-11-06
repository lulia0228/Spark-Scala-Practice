package com.xue.spark_core.ln01_wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount2 {
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
    //先分组
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordToOne.groupBy(t => t._1)
    //再聚合
    val wordToCount: RDD[(String, Int)] = wordGroup.map {
      case (word, list) => {
        list.reduce((t1, t2) => {
          (t1._1, t1._2 + t2._2)
        })
      }
    }

    //转换结果采集到控制台后打印
    val arr: Array[(String, Int)] = wordToCount.collect()
    arr.foreach(println(_))

    //3. 关闭连接
    sc.stop()
  }

}
