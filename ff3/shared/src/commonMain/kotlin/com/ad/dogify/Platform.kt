package com.ad.dogify

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform