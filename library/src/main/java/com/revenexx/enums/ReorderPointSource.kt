package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ReorderPointSource(val value: String) {
    @SerializedName("row")
    ROW("row"),
    @SerializedName("default")
    DEFAULT("default");

    override fun toString() = value
}