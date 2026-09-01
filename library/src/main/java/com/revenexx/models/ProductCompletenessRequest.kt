package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No body. Everything this needs is the path id and what the catalog already holds; send `{}`.
 */
class ProductCompletenessRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductCompletenessRequest(
        )
    }
}