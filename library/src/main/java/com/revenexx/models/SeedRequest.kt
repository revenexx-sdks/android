package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SeedRequest(
    /**
     * 
     */
    @SerializedName("menus")
    var menus: List<Any>?,

    /**
     * 
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