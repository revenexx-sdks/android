package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartMergeStrategy(val value: String) {
    @SerializedName("merge")
    MERGE("merge"),
    @SerializedName("replace")
    REPLACE("replace");

    override fun toString() = value
}