package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceOnRequestReason(val value: String) {
    @SerializedName("not_priced")
    NOT_PRICED("not_priced"),
    @SerializedName("on_request_entry")
    ON_REQUEST_ENTRY("on_request_entry"),
    @SerializedName("anonymous_denied")
    ANONYMOUS_DENIED("anonymous_denied"),
    @SerializedName("no_identity")
    NO_IDENTITY("no_identity");

    override fun toString() = value
}