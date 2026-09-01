package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The price list this answer came out of — enough to link to it or to explain the number to a merchant ("this came from the dealer list").
 */
data class PriceListRef(
    /**
     * The list’s unique per-tenant code.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The list, by id — the same id `GET /prices/lists/{id}` takes.
     */
    @SerializedName("id")
    var id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "id" to id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceListRef(
            code = map["code"] as? String,
            id = map["id"] as? String,
        )
    }
}