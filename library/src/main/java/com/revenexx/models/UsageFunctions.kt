package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * UsageFunctions
 */
data class UsageFunctions(
    /**
     * Aggregated number of functions build per period.
     */
    @SerializedName("builds")
    val builds: List<Metric>,

    /**
     * Aggregated number of failed function builds per period.
     */
    @SerializedName("buildsFailed")
    val buildsFailed: List<Metric>,

    /**
     * Total aggregated number of failed function builds.
     */
    @SerializedName("buildsFailedTotal")
    val buildsFailedTotal: Long,

    /**
     * Aggregated sum of functions build mbSeconds per period.
     */
    @SerializedName("buildsMbSeconds")
    val buildsMbSeconds: List<Metric>,

    /**
     * Total aggregated sum of functions build mbSeconds.
     */
    @SerializedName("buildsMbSecondsTotal")
    val buildsMbSecondsTotal: Long,

    /**
     * Aggregated sum of functions build storage per period.
     */
    @SerializedName("buildsStorage")
    val buildsStorage: List<Metric>,

    /**
     * total aggregated sum of functions build storage.
     */
    @SerializedName("buildsStorageTotal")
    val buildsStorageTotal: Long,

    /**
     * Aggregated number of successful function builds per period.
     */
    @SerializedName("buildsSuccess")
    val buildsSuccess: List<Metric>,

    /**
     * Total aggregated number of successful function builds.
     */
    @SerializedName("buildsSuccessTotal")
    val buildsSuccessTotal: Long,

    /**
     * Aggregated sum of  functions build compute time per period.
     */
    @SerializedName("buildsTime")
    val buildsTime: List<Metric>,

    /**
     * Total aggregated sum of functions build compute time.
     */
    @SerializedName("buildsTimeTotal")
    val buildsTimeTotal: Long,

    /**
     * Total aggregated number of functions build.
     */
    @SerializedName("buildsTotal")
    val buildsTotal: Long,

    /**
     * Aggregated number of functions deployment per period.
     */
    @SerializedName("deployments")
    val deployments: List<Metric>,

    /**
     * Aggregated number of  functions deployment storage per period.
     */
    @SerializedName("deploymentsStorage")
    val deploymentsStorage: List<Metric>,

    /**
     * Total aggregated sum of functions deployment storage.
     */
    @SerializedName("deploymentsStorageTotal")
    val deploymentsStorageTotal: Long,

    /**
     * Total aggregated number of functions deployments.
     */
    @SerializedName("deploymentsTotal")
    val deploymentsTotal: Long,

    /**
     * Aggregated number of  functions execution per period.
     */
    @SerializedName("executions")
    val executions: List<Metric>,

    /**
     * Aggregated number of functions mbSeconds per period.
     */
    @SerializedName("executionsMbSeconds")
    val executionsMbSeconds: List<Metric>,

    /**
     * Total aggregated sum of functions execution mbSeconds.
     */
    @SerializedName("executionsMbSecondsTotal")
    val executionsMbSecondsTotal: Long,

    /**
     * Aggregated number of functions execution compute time per period.
     */
    @SerializedName("executionsTime")
    val executionsTime: List<Metric>,

    /**
     * Total aggregated sum of functions  execution compute time.
     */
    @SerializedName("executionsTimeTotal")
    val executionsTimeTotal: Long,

    /**
     * Total  aggregated number of functions execution.
     */
    @SerializedName("executionsTotal")
    val executionsTotal: Long,

    /**
     * Aggregated number of functions per period.
     */
    @SerializedName("functions")
    val functions: List<Metric>,

    /**
     * Total aggregated number of functions.
     */
    @SerializedName("functionsTotal")
    val functionsTotal: Long,

    /**
     * Time range of the usage stats.
     */
    @SerializedName("range")
    val range: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "builds" to builds.map { it.toMap() } as Any,
        "buildsFailed" to buildsFailed.map { it.toMap() } as Any,
        "buildsFailedTotal" to buildsFailedTotal as Any,
        "buildsMbSeconds" to buildsMbSeconds.map { it.toMap() } as Any,
        "buildsMbSecondsTotal" to buildsMbSecondsTotal as Any,
        "buildsStorage" to buildsStorage.map { it.toMap() } as Any,
        "buildsStorageTotal" to buildsStorageTotal as Any,
        "buildsSuccess" to buildsSuccess.map { it.toMap() } as Any,
        "buildsSuccessTotal" to buildsSuccessTotal as Any,
        "buildsTime" to buildsTime.map { it.toMap() } as Any,
        "buildsTimeTotal" to buildsTimeTotal as Any,
        "buildsTotal" to buildsTotal as Any,
        "deployments" to deployments.map { it.toMap() } as Any,
        "deploymentsStorage" to deploymentsStorage.map { it.toMap() } as Any,
        "deploymentsStorageTotal" to deploymentsStorageTotal as Any,
        "deploymentsTotal" to deploymentsTotal as Any,
        "executions" to executions.map { it.toMap() } as Any,
        "executionsMbSeconds" to executionsMbSeconds.map { it.toMap() } as Any,
        "executionsMbSecondsTotal" to executionsMbSecondsTotal as Any,
        "executionsTime" to executionsTime.map { it.toMap() } as Any,
        "executionsTimeTotal" to executionsTimeTotal as Any,
        "executionsTotal" to executionsTotal as Any,
        "functions" to functions.map { it.toMap() } as Any,
        "functionsTotal" to functionsTotal as Any,
        "range" to range as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = UsageFunctions(
            builds = (map["builds"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            buildsFailed = (map["buildsFailed"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            buildsFailedTotal = (map["buildsFailedTotal"] as Number).toLong(),
            buildsMbSeconds = (map["buildsMbSeconds"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            buildsMbSecondsTotal = (map["buildsMbSecondsTotal"] as Number).toLong(),
            buildsStorage = (map["buildsStorage"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            buildsStorageTotal = (map["buildsStorageTotal"] as Number).toLong(),
            buildsSuccess = (map["buildsSuccess"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            buildsSuccessTotal = (map["buildsSuccessTotal"] as Number).toLong(),
            buildsTime = (map["buildsTime"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            buildsTimeTotal = (map["buildsTimeTotal"] as Number).toLong(),
            buildsTotal = (map["buildsTotal"] as Number).toLong(),
            deployments = (map["deployments"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            deploymentsStorage = (map["deploymentsStorage"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            deploymentsStorageTotal = (map["deploymentsStorageTotal"] as Number).toLong(),
            deploymentsTotal = (map["deploymentsTotal"] as Number).toLong(),
            executions = (map["executions"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            executionsMbSeconds = (map["executionsMbSeconds"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            executionsMbSecondsTotal = (map["executionsMbSecondsTotal"] as Number).toLong(),
            executionsTime = (map["executionsTime"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            executionsTimeTotal = (map["executionsTimeTotal"] as Number).toLong(),
            executionsTotal = (map["executionsTotal"] as Number).toLong(),
            functions = (map["functions"] as List<Map<String, Any>>).map { Metric.from(map = it) },
            functionsTotal = (map["functionsTotal"] as Number).toLong(),
            range = map["range"] as String,
        )
    }
}