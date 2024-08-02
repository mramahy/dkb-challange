package com.dkb.dkbchallange.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class ShortUrlService @Autowired constructor(private val environment: Environment){
    private val urls = ConcurrentHashMap<String, String>()

    private val baseUrl: String
        get() {
            val port = environment.getProperty("local.server.port")
            return "http://localhost:$port/"
        }

    fun getShortUrl(id: String): String? {
        return urls[id]
    }

    fun saveUrl(url: String): String {
        val id = hashUrl(url)
        urls[id] = url
        return "$baseUrl$id"
    }

    private fun hashUrl(url: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(url.toByteArray(StandardCharsets.UTF_8))
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes).substring(0, 8)

    }
}