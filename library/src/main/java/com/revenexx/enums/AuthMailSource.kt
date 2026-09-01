package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class AuthMailSource(val value: String) {
    @SerializedName("tenant")
    TENANT("tenant"),
    @SerializedName("platform")
    PLATFORM("platform");

    override fun toString() = value
}