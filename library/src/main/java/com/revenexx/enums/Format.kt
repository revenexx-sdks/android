package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Format(val value: String) {
    @SerializedName("csv")
    CSV("csv"),
    @SerializedName("xml")
    XML("xml"),
    @SerializedName("json")
    JSON("json"),
    @SerializedName("xlsx")
    XLSX("xlsx");

    override fun toString() = value
}