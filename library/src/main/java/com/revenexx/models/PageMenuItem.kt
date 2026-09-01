package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One entry of a navigation menu. Stored verbatim, so a theme may carry extra keys of its own alongside these.
 */
data class PageMenuItem<T>(
    /**
     * Sub-entries. This is how a two-level main navigation or a grouped footer is stored.
     */
    @SerializedName("items")
    var items: List<Any>?,

    /**
     * The words a visitor clicks.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Where the entry goes: a page slug this app serves, a path the theme routes, or an absolute URL to somewhere else.
     */
    @SerializedName("to")
    var to: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items as Any,
        "label" to label as Any,
        "to" to to as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            items: List<Any>?,
            label: String?,
            to: String?,
            data: Map<String, Any>
        ) = PageMenuItem<Map<String, Any>>(
            items,
            label,
            to,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = PageMenuItem<T>(
            items = map["items"] as? List<Any>,
            label = map["label"] as? String,
            to = map["to"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}