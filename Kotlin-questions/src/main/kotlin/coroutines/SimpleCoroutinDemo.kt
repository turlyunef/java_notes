package com.turlyunef.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


suspend fun main() = coroutineScope {
    println("Hello from main")
    launch {
        println("Hello from coroutine. Wait..")
        delay(4000)
        println("Bye from coroutine!")
    }
    println("Again in main")
}
