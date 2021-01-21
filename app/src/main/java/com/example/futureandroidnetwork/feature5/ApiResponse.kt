package com.example.futureandroidnetwork.feature5

data class ApiResponse(
    val data: User
)

data class User(
    val id: Int?,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val avatar: String?
)