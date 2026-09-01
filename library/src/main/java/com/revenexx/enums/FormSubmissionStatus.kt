package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class FormSubmissionStatus(val value: String) {
    @SerializedName("new")
    NEW("new"),
    @SerializedName("read")
    READ("read"),
    @SerializedName("archived")
    ARCHIVED("archived"),
    @SerializedName("spam")
    SPAM("spam");

    override fun toString() = value
}