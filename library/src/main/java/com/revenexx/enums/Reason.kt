package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Reason(val value: String) {
    @SerializedName("hard_bounce")
    HARD_BOUNCE("hard_bounce"),
    @SerializedName("complaint")
    COMPLAINT("complaint"),
    @SerializedName("unsubscribe")
    UNSUBSCRIBE("unsubscribe"),
    @SerializedName("manual")
    MANUAL("manual");

    override fun toString() = value
}