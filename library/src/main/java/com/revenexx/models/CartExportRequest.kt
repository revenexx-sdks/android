package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartExportFormat

/**
 * 
 */
data class CartExportRequest(
    /**
     * Ad-hoc export format (only without profile_id).
     */
    @SerializedName("format")
    var format: CartExportFormat?,

    /**
     * Export profile to run; ad-hoc JSON/CSV export when omitted.
     */
    @SerializedName("profile_id")
    var profile_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "format" to format?.value as Any,
        "profile_id" to profile_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartExportRequest(
            format = CartExportFormat.values().find { it.value == (map["format"] as? String) } ?: null,
            profile_id = map["profile_id"] as? String,
        )
    }
}