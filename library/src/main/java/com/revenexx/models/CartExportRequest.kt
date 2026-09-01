package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartExportFormat

/**
 * 
 */
data class CartExportRequest(
    /**
     * Format of an ad-hoc export, read only when no profile_id is sent. 'json' returns the whole `{cart, items}` document, 'csv' the lines alone. Default 'json'.
     */
    @SerializedName("format")
    var format: CartExportFormat?,

    /**
     * The export profile to run — one of the ids `GET /carts/io/profiles?direction=export` lists. Omit it for an ad-hoc export in the canonical shape, which is what `format` is for.
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