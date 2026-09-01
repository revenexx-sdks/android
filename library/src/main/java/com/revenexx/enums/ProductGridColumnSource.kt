package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ProductGridColumnSource(val value: String) {
    @SerializedName("column")
    COLUMN("column"),
    @SerializedName("attribute")
    ATTRIBUTE("attribute"),
    @SerializedName("resolved")
    RESOLVED("resolved");

    override fun toString() = value
}