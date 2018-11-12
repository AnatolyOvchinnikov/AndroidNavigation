package com.google.example

import java.util.*

class User(var name: String) {
    val uid: String by lazy {
        generateUniqueKey()
    }
    fun generateUniqueKey(): String {
        return Random().nextInt(100000).toString()
    }
}