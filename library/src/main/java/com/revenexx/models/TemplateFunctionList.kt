package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Function Templates List
 */
data class TemplateFunctionList(
    /**
     * List of templates.
     */
    @SerializedName("templates")
    val templates: List<TemplateFunction>,

    /**
     * Total number of templates that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "templates" to templates.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TemplateFunctionList(
            templates = (map["templates"] as List<Map<String, Any>>).map { TemplateFunction.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}