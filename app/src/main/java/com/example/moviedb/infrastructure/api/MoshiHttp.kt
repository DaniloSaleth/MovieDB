package com.example.moviedb.infrastructure.api

import retrofit2.Retrofit

interface MoshiHttp {
    fun getHttpConfig(): Retrofit
}

inline fun <reified T : Any> MoshiHttp.create(): T = getHttpConfig().create(T::class.java)