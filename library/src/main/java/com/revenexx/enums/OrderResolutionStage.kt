package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderResolutionStage(val value: String) {
    @SerializedName("complete")
    COMPLETE("complete"),
    @SerializedName("reject")
    REJECT("reject");

    override fun toString() = value
}