package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderVocabularyIndex(
    /**
     * This app's name — the part before the dot in the qualified id.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app publishes, without its values — fetch one with GET /orders/vocabularies/{name}.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<OrderVocabularySummary>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "vocabularies" to vocabularies?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = (map["vocabularies"] as List<Map<String, Any>>).map { OrderVocabularySummary.from(map = it) },
        )
    }
}