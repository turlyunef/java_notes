package com.turlyunef.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    println("Hello from main")
    var simpleInt = 123
    launch {
        simpleInt = 456
        println("Hello from coroutine. Wait..")

        delay(4000)
        simpleInt = 789
        println("Bye from coroutine!")
    }
    println("Again in main and simpleInt = $simpleInt")
    println("Wait...")

    delay(200)
    println("Again in main and simpleInt = $simpleInt")
    println("Wait...")

    delay(3810)
    println("Again in main and simpleInt = $simpleInt")
}
