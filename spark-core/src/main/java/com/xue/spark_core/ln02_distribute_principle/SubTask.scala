package com.xue.spark_core.ln02_distribute_principle

class SubTask extends Serializable {

  var data:List[Int] = _
  var logic:Int=>Int = _

  def compute(): List[Int] ={
    data.map(logic)
  }
}
