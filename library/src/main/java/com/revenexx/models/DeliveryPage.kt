package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One published page resolved for one language, ready to render: i18n fallback applied per field, blocks outside their publish window removed, library references expanded inline.
 */
data class DeliveryPage(
    /**
     * The page's block tree, keyed by field name — `{ "content": [ … ] }`. A theme renders the field it knows and ignores the rest.
     */
    @SerializedName("fields")
    var fields: Any?,

    /**
     * The page frame — everything a theme needs before it starts rendering blocks.
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