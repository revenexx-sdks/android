package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Scope(val value: String) {
    @SerializedName("all")
    ALL("all"),
    @SerializedName("marketing")
    MARKETING("marketing");

    override fun toString() = value
}