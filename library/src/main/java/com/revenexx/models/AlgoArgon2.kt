package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * AlgoArgon2
 */
data class AlgoArgon2(
    /**
     * Memory used to compute hash.
     */
    @SerializedName("memoryCost")
    val memoryCost: Long,

    /**
     * Number of threads used to compute hash.
     */
    @SerializedName("threads")
    val threads: Long,

    /**
     * Amount of time consumed to compute hash
     */
    @SerializedName("timeCost")
    val timeCost: Long,

    /**
     * Algo type.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "memoryCost" to memoryCost as Any,
        "threads" to threads as Any,
        "timeCost" to timeCost as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AlgoArgon2(
            memoryCost = (map["memoryCost"] as Number).toLong(),
            threads = (map["threads"] as Number).toLong(),
            timeCost = (map["timeCost"] as Number).toLong(),
            type = map["type"] as String,
        )
    }
}