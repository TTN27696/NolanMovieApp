package org.example.nolan_movie_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform