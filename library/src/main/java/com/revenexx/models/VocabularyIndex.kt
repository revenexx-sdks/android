package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class VocabularyIndex(
    /**
     * This app's name — the part before the dot in the qualified id `customers.<name>`.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app publishes, without their values.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "vocabularies" to vocabularies as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = VocabularyIndex(
            app = map["app"] as? String,
            vocabularies = map["vocabularies"] as? List<Any>,
        )
    }
}