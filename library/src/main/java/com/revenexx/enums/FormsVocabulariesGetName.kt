package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class FormsVocabulariesGetName(val value: String) {
    @SerializedName("form-statuses")
    FORM_STATUSES("form-statuses"),
    @SerializedName("submission-statuses")
    SUBMISSION_STATUSES("submission-statuses");

    override fun toString() = value
}