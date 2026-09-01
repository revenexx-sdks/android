package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class InventoriesVocabulariesGetName(val value: String) {
    @SerializedName("location-types")
    LOCATION_TYPES("location-types"),
    @SerializedName("movement-types")
    MOVEMENT_TYPES("movement-types"),
    @SerializedName("reservation-statuses")
    RESERVATION_STATUSES("reservation-statuses");

    override fun toString() = value
}