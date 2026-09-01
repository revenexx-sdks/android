package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Which of the three standard codes this call had to create and which were already there.
 */
data class OrderNumberRangesSeeded(
    /**
     * The codes that were created just now, with the standard format ORD-/DEL-/RET- and padding 6. Empty on every call after the first.
     */
    @SerializedName("created")
    var created: List<String>?,

    /**
     * The codes that were already there and were left EXACTLY as they are — a merchant who changed the prefix or the counter keeps their change. That is what makes this call safe to run again.
     */
    @SerializedName("existing")
    var existing: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created" to created as Any,
        "existing" to existing as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderNumberRangesSeeded(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
        )
    }
}