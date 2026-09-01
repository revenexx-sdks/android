package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class AppsCreateVcsDeploymentType(val value: String) {
    @SerializedName("branch")
    BRANCH("branch"),
    @SerializedName("commit")
    COMMIT("commit");

    override fun toString() = value
}