package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ShippingVocabularyIndex(
    /**
     * The app that owns these vocabularies — the part before the dot in a qualified id.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app publishes, without its values. Names only: fetch one to get the set.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<ShippingVocabularyIndexEntry>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "vocabularies" to vocabularies?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = (map["vocabularies"] as List<Map<String, Any>>).map { ShippingVocabularyIndexEntry.from(map = it) },
        )
    }
}