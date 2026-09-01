package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What this app publishes, without the values — one fetch a UI can cache and then pull only the vocabularies it renders.
 */
data class PriceVocabularyIndex(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app owns, sorted by name.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<PriceVocabularyRef>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "vocabularies" to vocabularies?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = (map["vocabularies"] as List<Map<String, Any>>).map { PriceVocabularyRef.from(map = it) },
        )
    }
}