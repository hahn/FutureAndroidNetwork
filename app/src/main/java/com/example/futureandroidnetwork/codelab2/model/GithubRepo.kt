package com.example.futureandroidnetwork.codelab2.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("full_name")
    val fullname: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("message")
    var message: String? = null
)