package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `assets` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class AssetsFilter<T>(
    /**
     * The literal `?asset_family_id=` value this call was understood to carry.
     */
    @SerializedName("asset_family_id")
    var asset_family_id: String?,

    /**
     * The literal `?attribute_values=` value this call was understood to carry.
     */
    @SerializedName("attribute_values")
    var attribute_values: String?,

    /**
     * The literal `?code=` value this call was understood to carry.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?delivery_path=` value this call was understood to carry.
     */
    @SerializedName("delivery_path")
    var delivery_path: String?,

    /**
     * The literal `?external_url=` value this call was understood to carry.
     */
    @SerializedName("external_url")
    var external_url: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?source=` value this call was understood to carry.
     */
    @SerializedName("source")
    var source: String?,

    /**
     * The literal `?storage_asset_id=` value this call was understood to carry.
     */
    @SerializedName("storage_asset_id")
    var storage_asset_id: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "asset_family_id" to asset_family_id as Any,
        "attribute_values" to attribute_values as Any,
        "code" to code as Any,
        "created_at" to created_at as Any,
        "delivery_path" to delivery_path as Any,
        "external_url" to external_url as Any,
        "id" to id as Any,
        "source" to source as Any,
        "storage_asset_id" to storage_asset_id as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            asset_family_id: String?,
            attribute_values: String?,
            code: String?,
            created_at: String?,
            delivery_path: String?,
            external_url: String?,
            id: String?,
            source: String?,
            storage_asset_id: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = AssetsFilter<Map<String, Any>>(
            asset_family_id,
            attribute_values,
            code,
            created_at,
            delivery_path,
            external_url,
            id,
            source,
            storage_asset_id,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AssetsFilter<T>(
            asset_family_id = map["asset_family_id"] as? String,
            attribute_values = map["attribute_values"] as? String,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            delivery_path = map["delivery_path"] as? String,
            external_url = map["external_url"] as? String,
            id = map["id"] as? String,
            source = map["source"] as? String,
            storage_asset_id = map["storage_asset_id"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}