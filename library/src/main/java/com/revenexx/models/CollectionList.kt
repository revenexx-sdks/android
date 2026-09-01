package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CollectionList(
    /**
     * Public collection names the tenant owns. These are the values accepted for the `collection` path parameter.
     */
    @SerializedName("collections")
    val collections: List<String>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "collections" to collections as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CollectionList(
            collections = map["collections"] as List<String>,
        )
    }
}