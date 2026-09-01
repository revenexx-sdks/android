package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PagesVocabularyIndexApp

/**
 * Which vocabularies this app publishes.
 */
data class PagesVocabularyIndex(
    /**
     * Always 'pages' — the first half of the qualified id a client holds.
     */
    @SerializedName("app")
    var app: PagesVocabularyIndexApp?,

    /**
     * One entry per vocabulary, without its values.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<PagesVocabularyRef>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app?.value as Any,
        "vocabularies" to vocabularies?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PagesVocabularyIndex(
            app = PagesVocabularyIndexApp.values().find { it.value == (map["app"] as? String) } ?: null,
            vocabularies = (map["vocabularies"] as List<Map<String, Any>>).map { PagesVocabularyRef.from(map = it) },
        )
    }
}