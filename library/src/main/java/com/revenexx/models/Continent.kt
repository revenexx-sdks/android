package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Continent
 */
data class Continent(
    /**
     * Continent two letter code.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Continent name.
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
        ) = Continent(
            code = map["code"] as String,
            name = map["name"] as String,
        )
    }
}