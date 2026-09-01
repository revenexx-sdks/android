package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingCarrierStatus

/**
 * Partial update — omitted fields keep their current value.
 */
data class ShippingCarrierUpdateRequest(
    /**
     * Stable carrier code, unique per tenant (e.g. dhl, dpd, gls). A method whose `carrier` text equals this code resolves to this carrier — that is the migration path off the free-text field. Deliberately no slug pattern: the column asks only for a non-empty string, and a contract stricter than the implementation would refuse codes merchants already keep.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The countries this carrier serves. ISO 3166-1 alpha-2 codes; null or an empty array means no restriction. Compared upper-cased, so a lower-case entry still matches. Declared as an array rather than the bare object a jsonb column derives to — this one is always a list. ANDed with the method's own restriction: a method may not be offered into a country its carrier does not reach.
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
     * Localized display names. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Free-form jsonb the platform never reads or validates — whatever the merchant or their integration needs to keep beside the row (a customer number with the carrier, an ERP key, a label-printer id). The shape varies BY INTEGRATION, not by anything this app knows, so no key is declared and none is reserved; the example is one plausible instance rather than a schema. A flat map of scalars is the convention, and nothing enforces it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Display name, as an operator typed it.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order among the carriers; ties fall back to whatever the database returns.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The class of service this row represents (default 'standard'), as a CODE into the tenant's own service levels (GET /shipping/service-levels). One row is one class: a carrier selling both a parcel and an express product is two rows. Deliberately not an enum here — the set is the merchant's, so a fixed list in this contract would make the gateway reject a level they created. A code the tenant does not keep is a 400 naming the codes they do.
     */
    @SerializedName("service_level")
    var service_level: String?,

    /**
     * Whether this carrier may be quoted (default 'active'). Anything else excludes every method that ships with it from POST /shipping/rates, with a reason. Tracking links are NOT gated on it — a retired carrier's old shipments stay resolvable.
     */
    @SerializedName("status")
    var status: ShippingCarrierStatus?,

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
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "service_level" to service_level as Any,
        "status" to status?.value as Any,
        "tracking_url_template" to tracking_url_template as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingCarrierUpdateRequest(
            code = map["code"] as? String,
            countries = map["countries"] as? List<String>,
            cutoff_time = map["cutoff_time"] as? String,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            handling_days = (map["handling_days"] as? Number)?.toLong(),
            labels = map["labels"] as? Any,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            service_level = map["service_level"] as? String,
            status = ShippingCarrierStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tracking_url_template = map["tracking_url_template"] as? String,
        )
    }
}