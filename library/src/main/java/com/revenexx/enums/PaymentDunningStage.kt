package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PaymentDunningStage(val value: String) {
    @SerializedName("none")
    NONE("none"),
    @SerializedName("reminder")
    REMINDER("reminder"),
    @SerializedName("overdue")
    OVERDUE("overdue");

    override fun toString() = value
}