package com.xue.spark_core.ln08_framework.application

import com.xue.spark_core.ln08_framework.common.TApplication
import com.xue.spark_core.ln08_framework.controller.WordCountController

object WordCountApplication extends App with TApplication{

    // 启动应用程序
    start(){
        val controller = new WordCountController()
        controller.dispatch()
    }

}
