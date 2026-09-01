package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class FormNotifySource(val value: String) {
    @SerializedName("form")
    FORM("form"),
    @SerializedName("tenant")
    TENANT("tenant");

    override fun toString() = value
}