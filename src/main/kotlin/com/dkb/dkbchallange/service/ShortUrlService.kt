package com.dkb.dkbchallange.service

import com.dkb.dkbchallange.repository.InMemoryUrlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*

@Service
class ShortUrlService @Autowired constructor(private val urlRepository: InMemoryUrlRepository){

    @Value("\${local.server.port}")
    val port: String? = null

    private val baseUrl: String
        get() {
            return "http://localhost:$port/url/"
        }

    fun getShortUrl(id: String): String? {
        return urlRepository.getUrl(id)
    }

    fun saveUrl(url: String): String {
        val id = hashUrl(url)
        urlRepository.saveUrl(id, url)
        return "$baseUrl$id"
    }

    private fun hashUrl(url: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(url.toByteArray(StandardCharsets.UTF_8))
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes).substring(0, 8)

    }
}