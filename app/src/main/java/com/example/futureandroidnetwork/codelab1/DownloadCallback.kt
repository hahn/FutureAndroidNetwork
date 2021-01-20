package com.example.futureandroidnetwork.codelab1

import java.io.InputStream

interface DownloadCallback<T> {
    fun onSuccess(result: T?)
    fun onFailure(ex: Exception)
}