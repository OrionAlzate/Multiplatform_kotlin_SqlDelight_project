package com.mantum.multiapp

import commantumdatabase.User
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class Greeting {
    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("https://reqres.in/api/users")


        return response.bodyAsText()
    }
}