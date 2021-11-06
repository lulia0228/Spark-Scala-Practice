package com.xue.spark_core.ln02_distribute

class SubTask extends Serializable {

  var data:List[Int] = _
  var logic:Int=>Int = _

  def compute(): List[Int] ={
    data.map(logic)
  }
}
