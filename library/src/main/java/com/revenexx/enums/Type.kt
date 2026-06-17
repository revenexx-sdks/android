package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Type(val value: String) {
    @SerializedName("commit")
    COMMIT("commit"),
    @SerializedName("branch")
    BRANCH("branch"),
    @SerializedName("tag")
    TAG("tag");

    override fun toString() = value
}