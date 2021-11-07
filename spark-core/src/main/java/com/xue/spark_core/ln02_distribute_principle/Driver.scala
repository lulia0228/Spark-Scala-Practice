package com.xue.spark_core.ln02_distribute_principle

import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

object Driver {
  def main(args: Array[String]): Unit = {

    //连接服务器1,2，发送数据
    val client1 = new Socket("localhost", 9999)
    println("已连接服务1")
    val client2 = new Socket("localhost", 8888)
    println("已连接服务2")
    val manager = new ManagerTask()

    val out1: OutputStream = client1.getOutputStream
    val objOut1 = new ObjectOutputStream(out1)
    val subTask1 = new SubTask()
    subTask1.data = manager.data.take(2)
    subTask1.logic = manager.logic
    objOut1.writeObject(subTask1)
    objOut1.flush()
    objOut1.close()
    client1.close()

    val out2: OutputStream = client2.getOutputStream
    val objOut2 = new ObjectOutputStream(out2)
    val subTask2 = new SubTask()
    subTask2.data = manager.data.takeRight(2)
    subTask2.logic = manager.logic
    objOut2.writeObject(subTask2)
    objOut2.flush()
    objOut2.close()
    client2.close()

  }

}
