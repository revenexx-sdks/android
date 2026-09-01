package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderListVocabularyIndex(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app publishes, without its values — the values are one call further down, at GET /orderlists/vocabularies/{name}.
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
        ) = OrderListVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = map["vocabularies"] as? List<Any>,
        )
    }
}