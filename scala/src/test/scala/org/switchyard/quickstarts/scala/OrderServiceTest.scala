package org.switchyard.quickstarts.scala

import org.junit._
import Assert._
import org.junit.runner.RunWith
import org.switchyard.test.SwitchYardRunner
import org.switchyard.test.SwitchYardTestCaseConfig
import org.switchyard.test.mixins.CDIMixIn
import org.switchyard.test.SwitchYardTestKit


@RunWith(classOf[SwitchYardRunner])
@SwitchYardTestCaseConfig(config = "switchyard-orderservice-test.xml", mixins = Array(classOf[CDIMixIn]))
class OrderServiceTest {
  
    var _testKit :SwitchYardTestKit = null

    @Test
    def sendOrder() = {
      val invoker = _testKit.newInvoker("OrderService.sendOrder")
      invoker.sendInOnly("dummy payload")
      assertTrue(true)
    }

}


