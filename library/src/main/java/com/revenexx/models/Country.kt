package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Country
 */
data class Country(
    /**
     * Country two-character ISO 3166-1 alpha code.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Country name.
     */
    @SerializedName("name")
    val name: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "name" to name as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Country(
            code = map["code"] as String,
            name = map["name"] as String,
        )
    }
}