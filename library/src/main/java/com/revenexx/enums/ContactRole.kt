package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ContactRole(val value: String) {
    @SerializedName("buyer")
    BUYER("buyer"),
    @SerializedName("approver")
    APPROVER("approver"),
    @SerializedName("admin")
    ADMIN("admin"),
    @SerializedName("requester")
    REQUESTER("requester");

    override fun toString() = value
}