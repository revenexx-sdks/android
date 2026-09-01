package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class StockMovementType(val value: String) {
    @SerializedName("inbound")
    INBOUND("inbound"),
    @SerializedName("adjustment")
    ADJUSTMENT("adjustment"),
    @SerializedName("reserve")
    RESERVE("reserve"),
    @SerializedName("release")
    RELEASE("release"),
    @SerializedName("shipment")
    SHIPMENT("shipment"),
    @SerializedName("restock")
    RESTOCK("restock");

    override fun toString() = value
}