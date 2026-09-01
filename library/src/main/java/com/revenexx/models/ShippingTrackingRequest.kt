package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One parcel, resolved into a tracking link by the carrier that owns the URL format.
 */
data class ShippingTrackingRequest(
    /**
     * Carrier code (what an order shipment already stores) or the carrier row id — a value matching the uuid form is read as the id, anything else as a code, case-insensitively. Must name a carrier THIS tenant keeps; one that does not is a 404.
     */
    @SerializedName("carrier")
    val carrier: String,

    /**
     * Destination ISO 3166-1 alpha-2 code — only needed by a template that names {country}. Upper-cased before substitution.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * Destination postcode — only needed by a template that names {postal_code}.
     */
    @SerializedName("postal_code")
    var postal_code: String?,

    /**
     * The carrier's tracking number. Required by every template that names {tracking_code}, which is all of them in the shipped catalog. URL-encoded before substitution, so a code with a space or a slash cannot reshape the link.
     */
    @SerializedName("tracking_code")
    var tracking_code: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "country" to country as Any,
        "postal_code" to postal_code as Any,
        "tracking_code" to tracking_code as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingTrackingRequest(
            carrier = map["carrier"] as String,
            country = map["country"] as? String,
            postal_code = map["postal_code"] as? String,
            tracking_code = map["tracking_code"] as? String,
        )
    }
}