package com.dkb.dkbchallange.rest

import com.dkb.dkbchallange.DkbAbstractTest
import com.dkb.dkbchallange.service.ShortUrlService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.Test

@SpringBootTest(properties = ["local.server.port=8080"], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ShortUrlControllerIT : DkbAbstractTest() {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var shortUrlService: ShortUrlService

    @Test
    fun `test redirect for valid short URL`() {
        val shortUrl = shortUrlService.saveUrl(url)
        val id = shortUrl.removePrefix(baseUrl)
        val response = restTemplate.getForEntity("$baseUrl/$id", String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `test redirect for non-existent short URL`() {
        val response: ResponseEntity<Unit> = restTemplate.getForEntity("$baseUrl/nonExistentId", Unit::class.java)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `test create for valid URL`() {
        val response: ResponseEntity<String> = restTemplate.postForEntity("$baseUrl?url=$url", null, String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertTrue(response.body!!.startsWith(baseUrl))
    }

    @Test
    fun `test create for invalid URL`() {
        val url = "invalid-url"
        val response: ResponseEntity<String> = restTemplate.postForEntity("$baseUrl?url=$url", null, String::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }
}