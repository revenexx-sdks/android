package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartIoApplyMode(val value: String) {
    @SerializedName("insert")
    INSERT("insert"),
    @SerializedName("append")
    APPEND("append"),
    @SerializedName("replace")
    REPLACE("replace");

    override fun toString() = value
}