package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CategoriesCreateRequest(
    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("parent_id")
    var parent_id: String?,

    /**
     * 
     */
    @SerializedName("xpath")
    var xpath: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("values")
    var values: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "labels" to labels as Any,
        "parent_id" to parent_id as Any,
        "path" to xpath as Any,
        "position" to position as Any,
        "values" to values as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoriesCreateRequest(
            code = map["code"] as String,
            labels = map["labels"] as? Any,
            parent_id = map["parent_id"] as? String,
            xpath = map["path"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            values = map["values"] as? Any,
        )
    }
}