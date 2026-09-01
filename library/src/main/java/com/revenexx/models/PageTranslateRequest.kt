package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The strings to translate. They are forwarded to the tenant's provider verbatim.
 */
data class PageTranslateRequest(
    /**
     * The strings to translate. This app reads no element of the list — the provider defines the contract, and the blökkli adapter sends the fields below.
     */
    @SerializedName("items")
    var items: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageTranslateRequest(
            items = map["items"] as? List<Any>,
        )
    }
}