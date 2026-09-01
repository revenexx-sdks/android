package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FormDefaultsResult(
    /**
     * Slugs this call created. On a tenant that has had the app installed for more than a moment this is empty — the sample form is seeded on `app.installed`.
     */
    @SerializedName("created")
    var created: List<String>?,

    /**
     * Slugs that were already there and were left alone. Nothing about them was overwritten — a form the merchant has edited stays edited.
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
        ) = FormDefaultsResult(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
        )
    }
}