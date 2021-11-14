package com.xue.spark_core.ln08_framework.service

import com.xue.spark_core.ln08_framework.common.TService
import com.xue.spark_core.ln08_framework.dao.WordCountDao
import org.apache.spark.rdd.RDD

/**
  * 服务层
  */

class WordCountService extends TService {

    private val wordCountDao = new WordCountDao()

    // 数据分析
    def dataAnalysis(): Array[(String, Int)] = {

        //val lines: RDD[String] = wordCountDao.readFile("datas")
        val lines: RDD[String] = wordCountDao.readFile("datas/word1")
        val words: RDD[String] = lines.flatMap(_.split(" "))
        val wordToOne: RDD[(String, Int)] = words.map(word=>(word,1))
        val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_+_)
        val array: Array[(String, Int)] = wordToSum.collect()
        array
    }
}
