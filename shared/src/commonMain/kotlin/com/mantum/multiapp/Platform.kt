package com.mantum.multiapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform