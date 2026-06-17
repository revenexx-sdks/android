package com.revenexx.android.utils

import android.content.Context
import com.revenexx.Client

object Client {
    lateinit var client : Client

    fun create(context: Context) {
        client = Client(context)
            .setEndpoint("https://api.revenexx.com")
            .setProject("65a8e2b4632c04b1f5da")
            .setSelfSigned(true)
    }
}