package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What was created and what was already there. Nothing is ever overwritten, so a non-empty `skipped` is the normal answer to a second run.
 */
data class SeedResult(
    /**
     * The menu half of the run.
     */
    @SerializedName("menus")
    var menus: Any?,

    /**
     * The page half of the run.
     */
    @SerializedName("pages")
    var pages: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "menus" to menus as Any,
        "pages" to pages as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SeedResult(
            menus = map["menus"] as? Any,
            pages = map["pages"] as? Any,
        )
    }
}