package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CustomersOrganizationsListStatus(val value: String) {
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("blocked")
    BLOCKED("blocked");

    override fun toString() = value
}