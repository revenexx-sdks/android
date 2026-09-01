package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CustomersVocabulariesGetName(val value: String) {
    @SerializedName("address-types")
    ADDRESS_TYPES("address-types"),
    @SerializedName("contact-event-kinds")
    CONTACT_EVENT_KINDS("contact-event-kinds"),
    @SerializedName("contact-statuses")
    CONTACT_STATUSES("contact-statuses"),
    @SerializedName("lifecycle-stages")
    LIFECYCLE_STAGES("lifecycle-stages"),
    @SerializedName("locales")
    LOCALES("locales"),
    @SerializedName("organization-statuses")
    ORGANIZATION_STATUSES("organization-statuses"),
    @SerializedName("payment-terms")
    PAYMENT_TERMS("payment-terms"),
    @SerializedName("registration-statuses")
    REGISTRATION_STATUSES("registration-statuses"),
    @SerializedName("roles")
    ROLES("roles"),
    @SerializedName("rule-matches")
    RULE_MATCHES("rule-matches"),
    @SerializedName("segment-sources")
    SEGMENT_SOURCES("segment-sources");

    override fun toString() = value
}