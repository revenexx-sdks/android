package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ContactActivityKind(val value: String) {
    @SerializedName("note")
    NOTE("note"),
    @SerializedName("call")
    CALL("call"),
    @SerializedName("email")
    EMAIL("email"),
    @SerializedName("meeting")
    MEETING("meeting"),
    @SerializedName("visit")
    VISIT("visit"),
    @SerializedName("task")
    TASK("task");

    override fun toString() = value
}