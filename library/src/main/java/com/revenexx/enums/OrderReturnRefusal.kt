package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderReturnRefusal(val value: String) {
    @SerializedName("wear_and_tear")
    WEAR_AND_TEAR("wear_and_tear"),
    @SerializedName("not_returnable")
    NOT_RETURNABLE("not_returnable");

    override fun toString() = value
}