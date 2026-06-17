package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Published page resolved for one language: nested block tree with i18n fallback applied and scheduled blocks filtered.
 */
data class DeliveryPage(
    /**
     * Field name → ordered block list ({ uuid, bundle, props, options, children }).
     */
    @SerializedName("fields")
    var fields: Any?,

    /**
     * 
     */
    @SerializedName("page")
    var page: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "fields" to fields as Any,
        "page" to page as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = DeliveryPage(
            fields = map["fields"] as? Any,
            page = map["page"] as? Any,
        )
    }
}