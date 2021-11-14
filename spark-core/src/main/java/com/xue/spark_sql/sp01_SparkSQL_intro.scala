package com.xue.spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object sp01_SparkSQL_intro {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    val df: DataFrame = sparkSession.read.json("datas/user.json")
    //df.show()

    //1.1 用SQL操作DataFrame
    df.createOrReplaceTempView("user")
    //val df1: DataFrame = sparkSession.sql("select * from user")
    //val df1: DataFrame = sparkSession.sql("select username,sex from user")
    val df1: DataFrame = sparkSession.sql("select avg(age) from user")
    df1.show()

    //1.2 用DSL操作DataFrame
    val df2: DataFrame = df.select("age", "username", "sex")
    df2.show()
    //涉及到类型转换操作，需要手动引入隐式转换
    import sparkSession.implicits._
    //df.select($"age"+1).show()
    df.select('age + 1).show()

    //2 DataSet  出现的最晚（spark1.6）
    // DataFrame其实是DataSet的特定泛型
    val ds: Dataset[Int] = List(1, 2, 3, 4).toDS()
    ds.show()
    // 所以上面DataFrame的操作都可用于ds

    // 3 rdd和DataFrame互转
    val rdd: RDD[(String, String, Int)] = sparkSession.sparkContext.makeRDD(
      List(("1", "zhangsan", 20), ("2", "lisu", 30)))
    val df3: DataFrame = rdd.toDF("id", "name", "age")
    df3.show()
    val rdd2: RDD[Row] = df3.rdd

    // 4 DataFrame和DataSet互转
    val ds1: Dataset[User] = df3.as[User]
    val df4: DataFrame = ds1.toDF()
    df4.show()

    // 5 rdd和DataSet互转
    val ds2: Dataset[User] = rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }.toDS()
    val rdd3: RDD[User] = ds2.rdd
    sparkSession.close()
  }

  case class User(id:String, name:String, age:Int)

}
