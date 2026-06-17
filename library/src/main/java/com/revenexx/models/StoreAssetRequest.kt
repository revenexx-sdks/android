package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.StoreAssetRequestVisibility

/**
 * 
 */
data class StoreAssetRequest(
    /**
     * 
     */
    @SerializedName("alt_text")
    var alt_text: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * 
     */
    @SerializedName("display_name")
    var display_name: String?,

    /**
     * 
     */
    @SerializedName("file")
    val file: String,

    /**
     * 
     */
    @SerializedName("folder_id")
    var folder_id: String?,

    /**
     * 
     */
    @SerializedName("keep_archive")
    var keep_archive: Boolean?,

    /**
     * 
     */
    @SerializedName("tags")
    var tags: List<String>?,

    /**
     * Archives only: unpack the members after upload (see AssetController).
     */
    @SerializedName("unpack")
    var unpack: Boolean?,

    /**
     * 
     */
    @SerializedName("visibility")
    var visibility: StoreAssetRequestVisibility?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "alt_text" to alt_text as Any,
        "description" to description as Any,
        "display_name" to display_name as Any,
        "file" to file as Any,
        "folder_id" to folder_id as Any,
        "keep_archive" to keep_archive as Any,
        "tags" to tags as Any,
        "unpack" to unpack as Any,
        "visibility" to visibility?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = StoreAssetRequest(
            alt_text = map["alt_text"] as? String,
            description = map["description"] as? String,
            display_name = map["display_name"] as? String,
            file = map["file"] as String,
            folder_id = map["folder_id"] as? String,
            keep_archive = map["keep_archive"] as? Boolean,
            tags = map["tags"] as? List<String>,
            unpack = map["unpack"] as? Boolean,
            visibility = StoreAssetRequestVisibility.values().find { it.value == (map["visibility"] as? String) } ?: null,
        )
    }
}