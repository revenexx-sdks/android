package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class AssetsSource(val value: String) {
    @SerializedName("storage")
    STORAGE("storage"),
    @SerializedName("external")
    EXTERNAL("external");

    override fun toString() = value
}