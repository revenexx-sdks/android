package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class HealthStatusStatus(val value: String) {
    @SerializedName("pass")
    PASS("pass"),
    @SerializedName("fail")
    FAIL("fail");

    override fun toString() = value
}