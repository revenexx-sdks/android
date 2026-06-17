package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * blökkli MutationResponseLike: success flag plus the full re-materialized editor state.
 */
data class MutationResponse(
    /**
     * Full editor state (see pages.editor.state).
     */
    @SerializedName("state")
    var state: Any?,

    /**
     * 
     */
    @SerializedName("success")
    var success: Boolean?,

    /**
     * 
     */
    @SerializedName("violations")
    var violations: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "state" to state as Any,
        "success" to success as Any,
        "violations" to violations as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MutationResponse(
            state = map["state"] as? Any,
            success = map["success"] as? Boolean,
            violations = map["violations"] as? List<Any>,
        )
    }
}