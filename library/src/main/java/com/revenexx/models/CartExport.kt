package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartIoFormat

/**
 * 
 */
data class CartExport(
    /**
     * The export itself. For json: `{ "cart": { name, status, currency, channel_id, item_count, subtotal }, "items": [ … ] }` — exactly what carts.import takes back, so an export round-trips. For csv: the lines as a CSV string, header first, with jsonb columns serialized as JSON text. Deliberately untyped, because a profile's mapping renames the columns and that mapping is the caller's own.
     */
    @SerializedName("content")
    var content: String?,

    /**
     * A suggested download name, built as `cart-<cart id>.<format>`. Nothing is stored under it; it is there so a browser download has a name that says which cart it is.
     */
    @SerializedName("filename")
    var filename: String?,

    /**
     * The format that ran — the profile's, or the ad-hoc one.
     */
    @SerializedName("format")
    var format: CartIoFormat?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "content" to content as Any,
        "filename" to filename as Any,
        "format" to format?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartExport(
            content = map["content"] as? String,
            filename = map["filename"] as? String,
            format = CartIoFormat.values().find { it.value == (map["format"] as? String) } ?: null,
        )
    }
}