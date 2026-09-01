package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MessageClass(val value: String) {
    @SerializedName("transactional")
    TRANSACTIONAL("transactional"),
    @SerializedName("marketing")
    MARKETING("marketing");

    override fun toString() = value
}