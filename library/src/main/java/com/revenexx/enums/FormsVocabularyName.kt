package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class FormsVocabularyName(val value: String) {
    @SerializedName("form-statuses")
    FORM_STATUSES("form-statuses"),
    @SerializedName("submission-statuses")
    SUBMISSION_STATUSES("submission-statuses");

    override fun toString() = value
}