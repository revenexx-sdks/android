package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Theme(val value: String) {
    @SerializedName("light")
    LIGHT("light"),
    @SerializedName("dark")
    DARK("dark");

    override fun toString() = value
}