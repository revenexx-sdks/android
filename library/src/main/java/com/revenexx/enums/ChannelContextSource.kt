package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelContextSource(val value: String) {
    @SerializedName("body")
    BODY("body"),
    @SerializedName("query")
    QUERY("query"),
    @SerializedName("header")
    HEADER("header"),
    @SerializedName("jwt")
    JWT("jwt"),
    @SerializedName("default")
    DEFAULT("default");

    override fun toString() = value
}