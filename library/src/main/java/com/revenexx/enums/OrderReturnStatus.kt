package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderReturnStatus(val value: String) {
    @SerializedName("registered")
    REGISTERED("registered"),
    @SerializedName("received")
    RECEIVED("received"),
    @SerializedName("completed")
    COMPLETED("completed"),
    @SerializedName("rejected")
    REJECTED("rejected");

    override fun toString() = value
}