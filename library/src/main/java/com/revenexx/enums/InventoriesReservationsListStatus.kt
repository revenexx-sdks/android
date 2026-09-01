package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class InventoriesReservationsListStatus(val value: String) {
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("released")
    RELEASED("released"),
    @SerializedName("committed")
    COMMITTED("committed");

    override fun toString() = value
}