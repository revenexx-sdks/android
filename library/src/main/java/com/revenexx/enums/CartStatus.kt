package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartStatus(val value: String) {
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("abandoned")
    ABANDONED("abandoned"),
    @SerializedName("ordered")
    ORDERED("ordered"),
    @SerializedName("merged")
    MERGED("merged");

    override fun toString() = value
}