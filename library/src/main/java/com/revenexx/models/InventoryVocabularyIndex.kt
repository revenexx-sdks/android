package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryVocabularyIndex(
    /**
     * This app's name — the part before the dot in a qualified vocabulary id such as `inventories.movement-types`.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app publishes, WITHOUT its values — the index a client reads to discover them. Fetch the values with GET /inventories/vocabularies/{name}.
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
        ) = InventoryVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = map["vocabularies"] as? List<Any>,
        )
    }
}