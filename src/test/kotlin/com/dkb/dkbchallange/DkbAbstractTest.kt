package com.dkb.dkbchallange

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["local.server.port=8080"])
abstract class DkbAbstractTest {

    protected val url = "http://example.com"

    @Value("\${local.server.port}")
    protected var port: Int = 8080

    protected lateinit var baseUrl : String

    @BeforeEach
    fun setUp() {
        baseUrl = "http://localhost:$port/url"
    }

}