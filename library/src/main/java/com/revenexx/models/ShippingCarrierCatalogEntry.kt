package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One carrier this app knows the facts for, exactly as it would be created.
 */
data class ShippingCarrierCatalogEntry(
    /**
     * The code the seeded row would carry, and the code a method's `carrier` text has to match to resolve to it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The countries this carrier serves. ISO 3166-1 alpha-2 codes; null or an empty array means no restriction. Compared upper-cased, so a lower-case entry still matches. Declared as an array rather than the bare object a jsonb column derives to — this one is always a list.
     */
    @SerializedName("countries")
    var countries: List<String>?,

    /**
     * This carrier's own daily pickup cut-off, HH:MM in 24-hour form, UTC. Overrides the tenant's cutoff_time for methods on this carrier — one shop-wide time cannot be both DHL's 16:00 and a forwarder's 12:00. Null or the empty string means this carrier declares none; any other shape is a 400, because a cut-off the estimator cannot read is a delivery promise silently computed without one.
     */
    @SerializedName("cutoff_time")
    var cutoff_time: String?,

    /**
     * Transit time upper bound, in calendar days from the ship date.
     */
    @SerializedName("eta_days_max")
    var eta_days_max: Long?,

    /**
     * Transit time lower bound, in calendar days from the ship date — inherited by any method on this carrier that states no ETA of its own.
     */
    @SerializedName("eta_days_min")
    var eta_days_min: Long?,

    /**
     * Days needed to make a consignment ready for THIS carrier, added to the ship date before the transit days. Overrides the tenant's handling_days.
     */
    @SerializedName("handling_days")
    var handling_days: Long?,

    /**
     * Localized display names the seed would carry. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * The display name the seeded row would carry. An existing row keeps the merchant's own name — the seed never writes over one.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Whether a fresh install starts with this carrier. False means this app knows how to describe it but only creates it when asked.
     */
    @SerializedName("seeded")
    var seeded: Boolean?,

    /**
     * Service-level code the seeded row carries — one of the tenant's own values.
     */
    @SerializedName("service_level")
    var service_level: String?,

    /**
     * Tracking page URL with {tracking_code} where the number goes; {postal_code} and {country} are also substituted, URL-encoded. Null for a carrier with no public tracking page.
     */
    @SerializedName("tracking_url_template")
    var tracking_url_template: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "countries" to countries as Any,
        "cutoff_time" to cutoff_time as Any,
        "eta_days_max" to eta_days_max as Any,
        "eta_days_min" to eta_days_min as Any,
        "handling_days" to handling_days as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "seeded" to seeded as Any,
        "service_level" to service_level as Any,
        "tracking_url_template" to tracking_url_template as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingCarrierCatalogEntry(
            code = map["code"] as? String,
            countries = map["countries"] as? List<String>,
            cutoff_time = map["cutoff_time"] as? String,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            handling_days = (map["handling_days"] as? Number)?.toLong(),
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            seeded = map["seeded"] as? Boolean,
            service_level = map["service_level"] as? String,
            tracking_url_template = map["tracking_url_template"] as? String,
        )
    }
}