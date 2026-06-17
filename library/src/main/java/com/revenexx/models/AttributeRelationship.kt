package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AttributeRelationshipStatus

/**
 * AttributeRelationship
 */
data class AttributeRelationship(
    /**
     * Attribute creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Attribute update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Is attribute an array?
     */
    @SerializedName("array")
    var array: Boolean?,

    /**
     * Error message. Displays error generated on failure of creating or deleting an attribute.
     */
    @SerializedName("error")
    val error: String,

    /**
     * Attribute Key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * How deleting the parent document will propagate to child documents.
     */
    @SerializedName("onDelete")
    val onDelete: String,

    /**
     * The ID of the related collection.
     */
    @SerializedName("relatedCollection")
    val relatedCollection: String,

    /**
     * The type of the relationship.
     */
    @SerializedName("relationType")
    val relationType: String,

    /**
     * Is attribute required?
     */
    @SerializedName("required")
    val required: Boolean,

    /**
     * Whether this is the parent or child side of the relationship
     */
    @SerializedName("side")
    val side: String,

    /**
     * Attribute status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerializedName("status")
    val status: AttributeRelationshipStatus,

    /**
     * Is the relationship two-way?
     */
    @SerializedName("twoWay")
    val twoWay: Boolean,

    /**
     * The key of the two-way relationship.
     */
    @SerializedName("twoWayKey")
    val twoWayKey: String,

    /**
     * Attribute type.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$updatedAt" to updatedAt as Any,
        "array" to array as Any,
        "error" to error as Any,
        "key" to key as Any,
        "onDelete" to onDelete as Any,
        "relatedCollection" to relatedCollection as Any,
        "relationType" to relationType as Any,
        "required" to required as Any,
        "side" to side as Any,
        "status" to status.value as Any,
        "twoWay" to twoWay as Any,
        "twoWayKey" to twoWayKey as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeRelationship(
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            array = map["array"] as? Boolean,
            error = map["error"] as String,
            key = map["key"] as String,
            onDelete = map["onDelete"] as String,
            relatedCollection = map["relatedCollection"] as String,
            relationType = map["relationType"] as String,
            required = map["required"] as Boolean,
            side = map["side"] as String,
            status = AttributeRelationshipStatus.values().find { it.value == map["status"] as String }!!,
            twoWay = map["twoWay"] as Boolean,
            twoWayKey = map["twoWayKey"] as String,
            type = map["type"] as String,
        )
    }
}