package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * blökkli MutationResponseLike: whether the call was applied, plus the FULL re-materialized editor state — so a client never has to re-fetch after a change.
 */
data class MutationResponse(
    /**
     * Everything the blökkli editor runs on, for one page in one language, materialized at the current point of the undo history. The theme adapter maps it 1:1 onto blökkli's MappedState.
     */
    @SerializedName("state")
    var state: EditorState?,

    /**
     * Whether the change was applied.
     */
    @SerializedName("success")
    var success: Boolean?,

    /**
     * Why the call was refused, when `success` is false.
     */
    @SerializedName("violations")
    var violations: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "state" to state?.toMap() as Any,
        "success" to success as Any,
        "violations" to violations as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MutationResponse(
            state = EditorState.from(map = map["state"] as Map<String, Any>),
            success = map["success"] as? Boolean,
            violations = map["violations"] as? List<Any>,
        )
    }
}