package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Message2Status(val value: String) {
    @SerializedName("draft")
    DRAFT("draft"),
    @SerializedName("processing")
    PROCESSING("processing"),
    @SerializedName("scheduled")
    SCHEDULED("scheduled"),
    @SerializedName("sent")
    SENT("sent"),
    @SerializedName("failed")
    FAILED("failed");

    override fun toString() = value
}