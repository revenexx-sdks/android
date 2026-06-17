package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Headers
 */
data class Headers(
    /**
     * Header name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Header value.
     */
    @SerializedName("value")
    val value: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name as Any,
        "value" to value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Headers(
            name = map["name"] as String,
            value = map["value"] as String,
        )
    }
}