package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Deployments List
 */
data class DeploymentList(
    /**
     * List of deployments.
     */
    @SerializedName("deployments")
    val deployments: List<Deployment>,

    /**
     * Total number of deployments that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "deployments" to deployments.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = DeploymentList(
            deployments = (map["deployments"] as List<Map<String, Any>>).map { Deployment.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}