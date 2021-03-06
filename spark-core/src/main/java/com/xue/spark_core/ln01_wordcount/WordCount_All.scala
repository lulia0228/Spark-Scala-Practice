package com.xue.spark_core.ln01_wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable

object WordCount_All {
  def main(args: Array[String]): Unit = {

    val sparConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)
    wordcount91011(sc)
    sc.stop()

  }

  // 1 groupBy
  def wordcount1(sc : SparkContext): Unit = {

    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val group: RDD[(String, Iterable[String])] = words.groupBy(word=>word)
    val wordCount: RDD[(String, Int)] = group.mapValues(iter=>iter.size)
  }

  // 2 groupByKey
  def wordcount2(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordOne: RDD[(String, Int)] = words.map((_,1))
    val group: RDD[(String, Iterable[Int])] = wordOne.groupByKey()
    val wordCount: RDD[(String, Int)] = group.mapValues(iter=>iter.size)
  }

  // 3 reduceByKey
  def wordcount3(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordOne: RDD[(String, Int)] = words.map((_,1))
    val wordCount: RDD[(String, Int)] = wordOne.reduceByKey(_+_)
  }

  // 4 aggregateByKey
  def wordcount4(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordOne: RDD[(String, Int)] = words.map((_,1))
    val wordCount: RDD[(String, Int)] = wordOne.aggregateByKey(0)(_+_, _+_)
  }

  // 5 foldByKey
  def wordcount5(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordOne: RDD[(String, Int)] = words.map((_,1))
    val wordCount: RDD[(String, Int)] = wordOne.foldByKey(0)(_+_)
  }

  // 6 combineByKey
  def wordcount6(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordOne: RDD[(String, Int)] = words.map((_,1))
    val wordCount: RDD[(String, Int)] = wordOne.combineByKey(
      v=>v,
      (x:Int, y) => x + y,
      (x:Int, y:Int) => x + y
    )
  }

  // 7 countByKey
  def wordcount7(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordOne: RDD[(String, Int)] = words.map((_,1))
    val wordCount: collection.Map[String, Long] = wordOne.countByKey()
  }

  // 8 countByValue
  def wordcount8(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))
    val wordCount: collection.Map[String, Long] = words.countByValue()
  }

  // 9 reduce, aggregate, fold
  def wordcount91011(sc : SparkContext): Unit = {
    val rdd: RDD[String] = sc.makeRDD(List("Hello Scala", "Hello Spark"))
    val words: RDD[String] = rdd.flatMap(_.split(" "))

    // ??????word, count???,(word, count)???
    // word => Map[(word,1)]
    val mapWord: RDD[mutable.Map[String, Long]] = words.map(
      word => {
        mutable.Map[String, Long]((word, 1))
      }
    )

    val wordCount: mutable.Map[String, Long] = mapWord.reduce(
      (map1, map2) => {
        map2.foreach {
          case (word, count) => {
            val newCount: Long = map1.getOrElse(word, 0L) + count
            map1.update(word, newCount)
          }
        }
        map1
      }
    )

    println(wordCount)
  }

}
