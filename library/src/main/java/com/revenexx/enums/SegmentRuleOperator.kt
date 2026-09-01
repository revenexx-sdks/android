package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class SegmentRuleOperator(val value: String) {
    @SerializedName("eq")
    EQ("eq"),
    @SerializedName("neq")
    NEQ("neq"),
    @SerializedName("gt")
    GT("gt"),
    @SerializedName("gte")
    GTE("gte"),
    @SerializedName("lt")
    LT("lt"),
    @SerializedName("lte")
    LTE("lte"),
    @SerializedName("in")
    IN("in"),
    @SerializedName("contains")
    CONTAINS("contains"),
    @SerializedName("starts_with")
    STARTS_WITH("starts_with"),
    @SerializedName("ends_with")
    ENDS_WITH("ends_with"),
    @SerializedName("is_empty")
    IS_EMPTY("is_empty"),
    @SerializedName("is_not_empty")
    IS_NOT_EMPTY("is_not_empty");

    override fun toString() = value
}