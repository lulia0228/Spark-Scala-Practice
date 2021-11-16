package com.xue.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

/**
 * 利用jdbc访问mysql
 */

object Spark04_SparkSQL_JDBC {

    def main(args: Array[String]): Unit = {

        // TODO 创建SparkSQL的运行环境
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
        import spark.implicits._

        // 读取MySQL数据
        val df: DataFrame = spark.read
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/runoob")
                .option("driver", "com.mysql.cj.jdbc.Driver") //旧版本驱动:com.mysql.jdbc.Driver
                .option("user", "root")
                .option("password", "Lh123456")
                .option("dbtable", "products")
                .load()
        df.show

        // 保存数据
        df.write
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/runoob")
                .option("driver", "com.mysql.cj.jdbc.Driver")
                .option("user", "root")
                .option("password", "Lh123456")
                .option("dbtable", "products1")
                .mode(SaveMode.Append)
                .save()


        // TODO 关闭环境
        spark.close()
    }
}
