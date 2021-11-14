package com.xue.spark_core.ln08_framework.common

import com.xue.spark_core.ln08_framework.util.EnvUtil
import org.apache.spark.rdd.RDD

trait TDao {

    def readFile(path:String): RDD[String] = {
        EnvUtil.take().textFile(path)
    }
}
