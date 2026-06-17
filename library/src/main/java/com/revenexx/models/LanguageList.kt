package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Languages List
 */
data class LanguageList(
    /**
     * List of languages.
     */
    @SerializedName("languages")
    val languages: List<Language>,

    /**
     * Total number of languages that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "languages" to languages.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LanguageList(
            languages = (map["languages"] as List<Map<String, Any>>).map { Language.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}