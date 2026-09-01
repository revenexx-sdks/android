package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelVocabularyRefName(val value: String) {
    @SerializedName("statuses")
    STATUSES("statuses"),
    @SerializedName("types")
    TYPES("types"),
    @SerializedName("unassigned-visibility")
    UNASSIGNED_VISIBILITY("unassigned-visibility");

    override fun toString() = value
}