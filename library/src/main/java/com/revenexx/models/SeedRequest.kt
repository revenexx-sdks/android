package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A theme's starting content. Both lists are optional; sending neither is a no-op.
 */
data class SeedRequest(
    /**
     * The menus to create. One with no key or no label is reported under `skipped`.
     */
    @SerializedName("menus")
    var menus: List<Any>?,

    /**
     * The pages to create. One that has no `slug` or no `title` is reported under `skipped` rather than refused, so one bad entry never loses the rest.
     */
    @SerializedName("pages")
    var pages: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "menus" to menus as Any,
        "pages" to pages as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SeedRequest(
            menus = map["menus"] as? List<Any>,
            pages = map["pages"] as? List<Any>,
        )
    }
}