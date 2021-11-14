package com.xue.spark_core.ln08_framework.common

import com.xue.spark_core.ln08_framework.util.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}

trait TApplication {

    //op是从外面传进来的一段语句
    def start(master:String ="local[*]", app:String = "Application")( op : => Unit ): Unit = {
        val sparConf: SparkConf = new SparkConf().setMaster(master).setAppName(app)
        val sc = new SparkContext(sparConf)
        EnvUtil.put(sc)

        try {
            op
        } catch {
            case ex => println(ex.getMessage)
        }

        // TODO 关闭连接
        sc.stop()
        EnvUtil.clear()
    }
}
