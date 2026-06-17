package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderAcknowledgeRequest(
    /**
     * The fulfilling system's order reference (e.g. the ERP order number).
     */
    @SerializedName("external_ref")
    var external_ref: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "external_ref" to external_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderAcknowledgeRequest(
            external_ref = map["external_ref"] as? String,
        )
    }
}