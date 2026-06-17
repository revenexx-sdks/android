package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Phones List
 */
data class PhoneList(
    /**
     * List of phones.
     */
    @SerializedName("phones")
    val phones: List<Phone>,

    /**
     * Total number of phones that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "phones" to phones.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PhoneList(
            phones = (map["phones"] as List<Map<String, Any>>).map { Phone.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}