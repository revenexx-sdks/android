package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderListCartMode(val value: String) {
    @SerializedName("append")
    APPEND("append"),
    @SerializedName("replace")
    REPLACE("replace");

    override fun toString() = value
}