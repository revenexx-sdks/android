package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Language
 */
data class Language(
    /**
     * Language two-character ISO 639-1 codes.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Language name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Language native name.
     */
    @SerializedName("nativeName")
    val nativeName: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "name" to name as Any,
        "nativeName" to nativeName as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Language(
            code = map["code"] as String,
            name = map["name"] as String,
            nativeName = map["nativeName"] as String,
        )
    }
}