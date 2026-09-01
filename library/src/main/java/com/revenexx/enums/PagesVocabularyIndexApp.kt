package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PagesVocabularyIndexApp(val value: String) {
    @SerializedName("pages")
    PAGES("pages");

    override fun toString() = value
}