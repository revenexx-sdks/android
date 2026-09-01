package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, echoed with the values as they arrived. A query parameter that is not a filterable column of this entity is DROPPED rather than refused, and is simply missing here — so an empty object next to a query string that had a filter in it means the filter was misspelled, and is the only way to tell that from a filter that matched nothing.
 */
data class FormListFilter<T>(
    /**
     * The `created_at` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The `id` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The `name` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The `slug` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * The `status` filter, verbatim as the query string carried it. A string here whatever the column's own type — and NOT necessarily one of the permitted values: `?status=zzz` is echoed back unchanged and matches nothing, which is the point of the echo.
     */
    @SerializedName("status")
    var status: String?,

    /**
     * The `updated_at` filter, verbatim as the query string carried it. A string here whatever the column's own type.
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
        "created_at" to created_at as Any,
        "id" to id as Any,
        "name" to name as Any,
        "slug" to slug as Any,
        "status" to status as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            created_at: String?,
            id: String?,
            name: String?,
            slug: String?,
            status: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = FormListFilter<Map<String, Any>>(
            created_at,
            id,
            name,
            slug,
            status,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FormListFilter<T>(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            name = map["name"] as? String,
            slug = map["slug"] as? String,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}