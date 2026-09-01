package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FormActionMapping(
    /**
     * The key in the submission `data` — i.e. the `name` of a definition node.
     */
    @SerializedName("source")
    var source: String?,

    /**
     * The column of the target entity it is written to.
     */
    @SerializedName("target")
    var target: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "source" to source as Any,
        "target" to target as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormActionMapping(
            source = map["source"] as? String,
            target = map["target"] as? String,
        )
    }
}