package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ChannelVocabularyIndex(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app owns, alphabetically: statuses, types, unassigned-visibility. Names only — fetch the values with GET /channels/vocabularies/{name}.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<ChannelVocabularyRef>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "vocabularies" to vocabularies?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = (map["vocabularies"] as List<Map<String, Any>>).map { ChannelVocabularyRef.from(map = it) },
        )
    }
}