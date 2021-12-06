package com.xue.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

/**
 * 本机 去hadoop安装目录(/usr/local/Cellar/hadoop/3.3.1)sbin下./start-all.sh启动hadoop,如果启动不了namenode，
 * 去libexec/bin下格式化一下  hdfs namenode -format , 应该已经通过core-site.xml解决了
 * 在浏览器中打开Resource Manager输入如下网址:http://localhost:9870/ 3.0以上端口移到9870
 * http://localhost:8042/ 可以看到节点信息和RM Home(http://liheng26demacbook-pro.local:8088/cluster)
 */

object Spark05_SparkSQL_Hive1 {

    def main(args: Array[String]): Unit = {
        //System.setProperty("HADOOP_USER_NAME", "root")

        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(sparkConf).getOrCreate()

        // 使用SparkSQL连接外置的Hive
        // 1. 拷贝hive-site.xml文件到resources?下 target的classes下会自动生成？
        // 2. 启用Hive的支持   enableHiveSupport
        // 3. 增加对应的依赖关系 （包含MySQL驱动）mysql-connector-java

        //spark.sql("set spark.sql.hive.convertMetastoreOrc = fasle")
        //先在hive中创建数据库  create database practice;
        spark.sql("use practice")

        // 准备数据
        spark.sql(
            """
              |CREATE TABLE IF NOT EXISTS `user_visit_action` (
              |  `date` string,
              |  `user_id` bigint,
              |  `session_id` string,
              |  `page_id` bigint,
              |  `action_time` string,
              |  `search_keyword` string,
              |  `click_category_id` bigint,
              |  `click_product_id` bigint,
              |  `order_category_ids` string,
              |  `order_product_ids` string,
              |  `pay_category_ids` string,
              |  `pay_product_ids` string,
              |  `city_id` bigint)
              |row format delimited fields terminated by '\t'
            """.stripMargin)

        spark.sql(
            """
              |load data local inpath 'spark_sql_data/user_visit_action.txt' into table practice.user_visit_action
            """.stripMargin)

        spark.sql(
            """
              |CREATE TABLE IF NOT EXISTS `product_info`(
              |  `product_id` bigint,
              |  `product_name` string,
              |  `extend_info` string)
              |row format delimited fields terminated by '\t'
            """.stripMargin)

        spark.sql(
            """
              |load data local inpath 'spark_sql_data/product_info.txt' into table practice.product_info
            """.stripMargin)

        spark.sql(
            """
              |CREATE TABLE IF NOT EXISTS `city_info`(
              |  `city_id` bigint,
              |  `city_name` string,
              |  `area` string)
              |row format delimited fields terminated by '\t'
            """.stripMargin)

        spark.sql(
            """
              |load data local inpath 'spark_sql_data/city_info.txt' into table practice.city_info
            """.stripMargin)

        spark.sql("""select * from city_info""").show


        spark.close()
    }
}
