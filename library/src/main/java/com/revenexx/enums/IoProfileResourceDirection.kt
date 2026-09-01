package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class IoProfileResourceDirection(val value: String) {
    @SerializedName("import")
    IMPORT("import"),
    @SerializedName("export")
    EXPORT("export");

    override fun toString() = value
}