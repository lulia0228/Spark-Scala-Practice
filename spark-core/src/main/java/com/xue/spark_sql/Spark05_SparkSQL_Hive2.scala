package com.xue.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

object Spark05_SparkSQL_Hive2 {

    def main(args: Array[String]): Unit = {
        System.setProperty("HADOOP_USER_NAME", "root")
        // TODO 创建SparkSQL的运行环境
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(sparkConf).getOrCreate()
        spark.sql("use practice")
        // 使用SparkSQL连接外置的Hive
        // 1. 拷贝hive-site.xml文件到resources?下target的classes下会自动生成？
        // 2. 启用Hive的支持
        // 3. 增加对应的依赖关系（包含MySQL驱动）
        val df: DataFrame = spark.sql("show tables")
        df.show()
        spark.sql("desc formatted city_info").show
        spark.sql("select city_name from city_info").show

        // TODO 关闭环境
        spark.close()
    }
}
