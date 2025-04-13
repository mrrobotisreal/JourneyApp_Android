package io.winapps.journeyapp.services

object Api {
    val service: ApiService by lazy {
        ApiClient.retrofit.create(ApiService::class.java)
    }
}