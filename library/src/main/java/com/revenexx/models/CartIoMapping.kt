package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Baseline-IO-compatible column mapping. An empty object (or null) is identity: the full canonical shape, every field under its own name.
 */
data class CartIoMapping(
    /**
     * Renames, in order. On export the row is narrowed to these columns; on import a column that is not listed is ignored. Omit or leave empty for identity.
     */
    @SerializedName("columns")
    var columns: List<CartIoMappingColumn>?,

    /**
     * Fields that identify a line in the payload — what the bundled quick-order template sets to ['sku'].
     */
    @SerializedName("keys")
    var keys: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "columns" to columns?.map { it.toMap() } as Any,
        "keys" to keys as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartIoMapping(
            columns = (map["columns"] as List<Map<String, Any>>).map { CartIoMappingColumn.from(map = it) },
            keys = map["keys"] as? List<String>,
        )
    }
}