package com.dkb.dkbchallange

import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class DkbAbstractTest {

    protected val url = "http://example.com"

    @LocalServerPort
    protected var port: Int = 0

    protected lateinit var baseUrl : String

    @BeforeEach
    fun setUp() {
        baseUrl = "http://localhost:$port/"
    }

}