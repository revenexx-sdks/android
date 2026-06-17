package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class StoreAssetRequestVisibility(val value: String) {
    @SerializedName("public")
    PUBLIC("public"),
    @SerializedName("private")
    PRIVATE("private");

    override fun toString() = value
}