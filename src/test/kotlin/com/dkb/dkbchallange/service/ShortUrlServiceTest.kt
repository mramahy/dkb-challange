package com.dkb.dkbchallange.service


import com.dkb.dkbchallange.DkbAbstractTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortUrlServiceTest : DkbAbstractTest() {

    @Autowired
    private lateinit var shortUrlService: ShortUrlService

    @Test
    fun `test saveUrl saves the URL and returns the correct short URL`() {
        val shortUrl = shortUrlService.saveUrl(url)
        val id = shortUrl.removePrefix(baseUrl)

        assertEquals(/* expected = */ url, /* actual = */ shortUrlService.getShortUrl(id))
    }

    @Test
    fun `test getShortUrl returns null for a non-existent ID`() {
        assertNull(shortUrlService.getShortUrl("nonExistentId"))
    }
}