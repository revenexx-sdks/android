package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PagesVocabularyApp(val value: String) {
    @SerializedName("pages")
    PAGES("pages");

    override fun toString() = value
}