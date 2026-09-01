package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Status(val value: String) {
    @SerializedName("invited")
    INVITED("invited"),
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("blocked")
    BLOCKED("blocked");

    override fun toString() = value
}