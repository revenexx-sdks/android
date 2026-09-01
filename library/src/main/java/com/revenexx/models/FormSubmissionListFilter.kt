package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, echoed with the values as they arrived. A query parameter that is not a filterable column of this entity is DROPPED rather than refused, and is simply missing here — so an empty object next to a query string that had a filter in it means the filter was misspelled, and is the only way to tell that from a filter that matched nothing.
 */
data class FormSubmissionListFilter<T>(
    /**
     * The `created_at` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The `form_id` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("form_id")
    var form_id: String?,

    /**
     * The `form_slug` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("form_slug")
    var form_slug: String?,

    /**
     * The `id` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The `source` filter, verbatim as the query string carried it. A string here whatever the column's own type.
     */
    @SerializedName("source")
    var source: String?,

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
        "form_id" to form_id as Any,
        "form_slug" to form_slug as Any,
        "id" to id as Any,
        "source" to source as Any,
        "status" to status as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            created_at: String?,
            form_id: String?,
            form_slug: String?,
            id: String?,
            source: String?,
            status: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = FormSubmissionListFilter<Map<String, Any>>(
            created_at,
            form_id,
            form_slug,
            id,
            source,
            status,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FormSubmissionListFilter<T>(
            created_at = map["created_at"] as? String,
            form_id = map["form_id"] as? String,
            form_slug = map["form_slug"] as? String,
            id = map["id"] as? String,
            source = map["source"] as? String,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}