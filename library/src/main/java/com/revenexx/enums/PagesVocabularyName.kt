package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PagesVocabularyName(val value: String) {
    @SerializedName("edit-state-statuses")
    EDIT_STATE_STATUSES("edit-state-statuses"),
    @SerializedName("page-statuses")
    PAGE_STATUSES("page-statuses"),
    @SerializedName("translation-statuses")
    TRANSLATION_STATUSES("translation-statuses");

    override fun toString() = value
}