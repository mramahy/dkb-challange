package com.dkb.dkbchallange.rest

import com.dkb.dkbchallange.service.ShortUrlService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/")
class ShortUrlController(private val service: ShortUrlService) {

    @GetMapping(value = ["/{id}"])
    fun redirect(@PathVariable id: String): ResponseEntity<Unit> {
        val url = service.getShortUrl(id)
        return if (url != null) {
            ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", url)
                .build()
        }
        else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun create(@RequestParam("url") url: String): ResponseEntity<String> {
        val uri = URI(url)
        return if (uri.scheme == "http" || uri.scheme == "https") {
            val shortUrl = service.saveUrl(url)
            ResponseEntity(shortUrl, HttpStatus.OK)
        } else
            ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}