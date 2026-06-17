package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class MenuUpdateRequest(
    /**
     * 
     */
    @SerializedName("items")
    var items: List<Any>?,

    /**
     * 
     */
    @SerializedName("label")
    var label: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items as Any,
        "label" to label as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MenuUpdateRequest(
            items = map["items"] as? List<Any>,
            label = map["label"] as? String,
        )
    }
}