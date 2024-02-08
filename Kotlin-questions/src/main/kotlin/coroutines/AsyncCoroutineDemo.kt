package com.turlyunef.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun main() = coroutineScope {
    println("Hello from main")
    val futureDeferred = async {
        println("Hello from coroutine")
        123
    }
    println("Result is ${futureDeferred.await()}")
}