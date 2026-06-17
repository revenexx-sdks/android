package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * AlgoScryptModified
 */
data class AlgoScryptModified(
    /**
     * Salt used to compute hash.
     */
    @SerializedName("salt")
    val salt: String,

    /**
     * Separator used to compute hash.
     */
    @SerializedName("saltSeparator")
    val saltSeparator: String,

    /**
     * Key used to compute hash.
     */
    @SerializedName("signerKey")
    val signerKey: String,

    /**
     * Algo type.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "salt" to salt as Any,
        "saltSeparator" to saltSeparator as Any,
        "signerKey" to signerKey as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AlgoScryptModified(
            salt = map["salt"] as String,
            saltSeparator = map["saltSeparator"] as String,
            signerKey = map["signerKey"] as String,
            type = map["type"] as String,
        )
    }
}