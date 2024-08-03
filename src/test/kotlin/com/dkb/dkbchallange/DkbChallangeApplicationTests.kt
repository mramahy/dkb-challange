package com.dkb.dkbchallange

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["local.server.port=8080"])
class DkbChallangeApplicationTests {

    @Test
    fun contextLoads() {
    }

}
