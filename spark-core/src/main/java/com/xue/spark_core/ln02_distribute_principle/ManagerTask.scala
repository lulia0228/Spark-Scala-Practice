package com.xue.spark_core.ln02_distribute_principle

class ManagerTask {

  val data = List(1,2,3,4)
  //val logic: Int => Int = (num:Int)=>{num*2}
  val logic: Int => Int = _ * 2

}
