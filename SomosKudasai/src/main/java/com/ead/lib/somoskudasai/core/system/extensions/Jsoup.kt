package com.ead.lib.somoskudasai.core.system.extensions

import kotlinx.coroutines.suspendCancellableCoroutine
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import kotlin.coroutines.resume

suspend fun Document.suspend() : Document? {
    return suspendCancellableCoroutine { continuation ->
        when (connection().response().statusCode()) {
            in 400..599 -> continuation.resume(null)
            in 200..299 -> continuation.resume(this)
        }
    }
}

fun Elements.src() : String {
    return attr("src")
}

fun Elements.href() : String {
    return attr("href")

}