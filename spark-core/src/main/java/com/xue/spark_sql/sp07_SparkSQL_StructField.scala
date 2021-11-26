package com.xue.spark_sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{LongType, StructField, StructType}

object sp07_SparkSQL_StructField {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().
      set("hive.exec.dynamic.partition.mode", "nonstrict").
      set("spark.speculation", "true").
      set("spark.executor.extraJavaOptions", "-Djava.util.Arrays.useLegacyMergeSort=true").
      setMaster("local[*]").
      setAppName("study")

    val spark: SparkSession = SparkSession.
      builder().
      enableHiveSupport().
      appName("quality").
      config(sparkConf).
      getOrCreate()

    val lis= List(1083817598L,1082962258L)
    val tag_ids: RDD[Long] = spark.sparkContext.parallelize(lis)
    val filed = "case_id"
    val fields: Array[StructField] = filed.split(" ").
      map((filed_name: String) => StructField(filed_name, LongType, nullable = true))
    val schema: StructType = StructType(fields)
    val rowRDD: RDD[Row] = tag_ids.map((att: Long) => Row(att))
    val caseId_df: DataFrame = spark.createDataFrame(rowRDD, schema)
    caseId_df.show(10)
    caseId_df.createOrReplaceTempView("caseIds")


//    spark.sql("use my_hive_table")
//    spark.sql("set hive.exec.dynamic.partition = true")
//    spark.sql("set hive.exec.dynamic.partition.mode = nonstrict")
//
//    val case_call_relation: DataFrame = spark.sql(
//      s"""
//    select case_id, lifecycle, call_id, type, add_time, staff_id
//    from
//    mart_csp.dim_cs_case_call_rela
//    where case_id in (select case_id from caseIds)
//    and partition_date between '2021-05-30' and '2021-10-25'
//    """)
//    case_call_relation.createOrReplaceTempView("case_call_relation")

  }






}


