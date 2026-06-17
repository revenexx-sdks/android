package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PageCreateRequest(
    /**
     * 
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * 
     */
    @SerializedName("hostOptions")
    var hostOptions: Any?,

    /**
     * 
     */
    @SerializedName("meta")
    var meta: Any?,

    /**
     * 
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * 
     */
    @SerializedName("sourceLanguage")
    var sourceLanguage: String?,

    /**
     * 
     */
    @SerializedName("title")
    val title: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "hostOptions" to hostOptions as Any,
        "meta" to meta as Any,
        "slug" to slug as Any,
        "sourceLanguage" to sourceLanguage as Any,
        "title" to title as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCreateRequest(
            bundle = map["bundle"] as? String,
            hostOptions = map["hostOptions"] as? Any,
            meta = map["meta"] as? Any,
            slug = map["slug"] as? String,
            sourceLanguage = map["sourceLanguage"] as? String,
            title = map["title"] as String,
        )
    }
}