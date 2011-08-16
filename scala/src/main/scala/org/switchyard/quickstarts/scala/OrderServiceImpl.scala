package org.switchyard.quickstarts.scala

import org.switchyard.component.bean.Service;

@Service(classOf[OrderService])
class OrderServiceImpl extends OrderService {
  
  def sendOrder(order :String) = {
    println("Order : " + order)
    "processed: " + order
  }
  
}