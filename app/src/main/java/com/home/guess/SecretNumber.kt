package com.home.guess

import java.util.*

class SecretNumber {
    // nextInt(10): 0~9
    val secret = Random().nextInt(10) + 1
    var count = 0

    fun validate(number: Int) : Int {
        count++
        return number - secret
    }
}

fun  main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secret)
    println("${secretNumber.validate(2)}, count: ${secretNumber.count}")
}