package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The market that was read from, resolved — so a caller who passed a code back gets the uuid, and one who passed a uuid gets the code the rest of the platform stores.
 */
data class MarketRef(
    /**
     * The source market's code — the value other apps scope by.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The source market's primary key.
     */
    @SerializedName("id")
    var id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "id" to id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketRef(
            code = map["code"] as? String,
            id = map["id"] as? String,
        )
    }
}