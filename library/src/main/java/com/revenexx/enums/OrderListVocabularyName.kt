package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderListVocabularyName(val value: String) {
    @SerializedName("kinds")
    KINDS("kinds");

    override fun toString() = value
}