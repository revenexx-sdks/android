package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartIoMappingColumn(
    /**
     * The cart or line field, spelled as this app spells it — one of the canonical column names.
     */
    @SerializedName("from")
    val from: String,

    /**
     * What that field is called on the outside: the CSV header, or the JSON key of the system on the other end.
     */
    @SerializedName("to")
    val to: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "from" to from as Any,
        "to" to to as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartIoMappingColumn(
            from = map["from"] as String,
            to = map["to"] as String,
        )
    }
}