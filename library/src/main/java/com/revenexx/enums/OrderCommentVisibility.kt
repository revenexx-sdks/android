package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderCommentVisibility(val value: String) {
    @SerializedName("internal")
    INTERNAL("internal"),
    @SerializedName("customer")
    CUSTOMER("customer");

    override fun toString() = value
}