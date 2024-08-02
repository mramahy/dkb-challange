package com.dkb.dkbchallange.repository

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryUrlRepository {
    private val urls = ConcurrentHashMap<String, String>()

    fun getUrl(id: String): String? {
        return urls[id]
    }

    fun saveUrl(id: String, url: String) {
        urls[id] = url
    }
}