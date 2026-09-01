package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What to record about this publication.
 */
data class PagePublishRequest(
    /**
     * Publish despite violations. Without it a page with unresolved violations answers 422 and nothing is written.
     */
    @SerializedName("force")
    var force: Boolean?,

    /**
     * What to call this publication in the page's history — "Autumn campaign" rather than a timestamp.
     */
    @SerializedName("label")
    var label: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "force" to force as Any,
        "label" to label as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PagePublishRequest(
            force = map["force"] as? Boolean,
            label = map["label"] as? String,
        )
    }
}