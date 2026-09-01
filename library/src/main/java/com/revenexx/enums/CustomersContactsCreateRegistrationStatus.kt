package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CustomersContactsCreateRegistrationStatus(val value: String) {
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("approved")
    APPROVED("approved");

    override fun toString() = value
}