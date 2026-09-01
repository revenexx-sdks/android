package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ValidationFailedResponseStatus(val value: String) {
    @SerializedName("invalid")
    INVALID("invalid");

    override fun toString() = value
}