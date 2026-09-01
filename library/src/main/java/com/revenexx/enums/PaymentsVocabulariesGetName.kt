package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PaymentsVocabulariesGetName(val value: String) {
    @SerializedName("dunning-stages")
    DUNNING_STAGES("dunning-stages"),
    @SerializedName("fee-types")
    FEE_TYPES("fee-types"),
    @SerializedName("method-kinds")
    METHOD_KINDS("method-kinds"),
    @SerializedName("statuses")
    STATUSES("statuses");

    override fun toString() = value
}