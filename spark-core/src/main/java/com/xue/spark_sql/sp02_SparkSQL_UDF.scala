package com.xue.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object sp02_SparkSQL_UDF {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    val df: DataFrame = sparkSession.read.json("datas/user.json")

    //1 用户自定义函数    实现对子段的再处理
    sparkSession.udf.register("prefix", (s:String)=>"name:"+s)
    df.createOrReplaceTempView("user")
    val df1: DataFrame = sparkSession.sql("select prefix(username), sex from user")
    df1.show()

    sparkSession.close()
  }

}
