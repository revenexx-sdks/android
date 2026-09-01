package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartVocabularyRefName(val value: String) {
    @SerializedName("io-apply-modes")
    IO_APPLY_MODES("io-apply-modes"),
    @SerializedName("io-directions")
    IO_DIRECTIONS("io-directions"),
    @SerializedName("io-entities")
    IO_ENTITIES("io-entities"),
    @SerializedName("io-formats")
    IO_FORMATS("io-formats"),
    @SerializedName("item-types")
    ITEM_TYPES("item-types"),
    @SerializedName("statuses")
    STATUSES("statuses");

    override fun toString() = value
}