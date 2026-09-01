package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class LifecycleStageCreateRequestTone(val value: String) {
    @SerializedName("neutral")
    NEUTRAL("neutral"),
    @SerializedName("info")
    INFO("info"),
    @SerializedName("success")
    SUCCESS("success"),
    @SerializedName("warning")
    WARNING("warning"),
    @SerializedName("danger")
    DANGER("danger");

    override fun toString() = value
}