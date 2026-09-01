package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A Revenexx step marker. The storefront cuts the flat array at each marker and renders the nodes that follow it as one wizard step, then removes the marker before FormKit renders anything. A definition with no marker is a single-step form.
 */
data class FormKitStepMarker<T>(
    /**
     * Stable id for the step, so a client can address it.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * What the step is: 'fields' for a normal step, 'thankyou' for the confirmation panel shown after a successful submit.
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * The step heading the visitor reads.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "kind" to kind as Any,
        "title" to title as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            id: String?,
            kind: String?,
            title: String?,
            data: Map<String, Any>
        ) = FormKitStepMarker<Map<String, Any>>(
            id,
            kind,
            title,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FormKitStepMarker<T>(
            id = map["id"] as? String,
            kind = map["kind"] as? String,
            title = map["title"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}