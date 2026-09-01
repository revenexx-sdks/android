package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CreateImportTarget(val value: String) {
    @SerializedName("live")
    LIVE("live"),
    @SerializedName("shadow")
    SHADOW("shadow");

    override fun toString() = value
}