package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ContactRegistrationStatus(val value: String) {
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("approved")
    APPROVED("approved"),
    @SerializedName("rejected")
    REJECTED("rejected");

    override fun toString() = value
}