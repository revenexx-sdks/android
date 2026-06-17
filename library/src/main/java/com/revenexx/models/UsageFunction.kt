package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * UsageFunction
 */
data class UsageFunction(
    /**
     * Aggregated number of function builds per period.
     */
    @SerializedName("builds")
    val builds: List<Metric>,

    /**
     * Aggregated number of failed builds per period.
     */
    @SerializedName("buildsFailed")
    val buildsFailed: List<Metric>,

    /**
     * Total aggregated number of failed function builds.
     */
    @SerializedName("buildsFailedTotal")
    val buildsFailedTotal: Long,

    /**
     * Aggregated number of function builds mbSeconds per period.
     */
    @SerializedName("buildsMbSeconds")
    val buildsMbSeconds: List<Metric>,

    /**
     * Total aggregated sum of function builds mbSeconds.
     */
    @SerializedName("buildsMbSecondsTotal")
    val buildsMbSecondsTotal: Long,

    /**
     * Aggregated sum of function builds storage per period.
     */
    @SerializedName("buildsStorage")
    val buildsStorage: List<Metric>,

    /**
     * total aggregated sum of function builds storage.
     */
    @SerializedName("buildsStorageTotal")
    val buildsStorageTotal: Long,

    /**
     * Aggregated number of successful builds per period.
     */
    @SerializedName("buildsSuccess")
    val buildsSuccess: List<Metric>,

    /**
     * Total aggregated number of successful function builds.
     */
    @SerializedName("buildsSuccessTotal")
    val buildsSuccessTotal: Long,

    /**
     * Aggregated sum of function builds compute time per period.
     */
    @SerializedName("buildsTime")
    val buildsTime: List<Metric>,

    /**
     * Average builds compute time.
     */
    @SerializedName("buildsTimeAverage")
    val buildsTimeAverage: Long,

    /**
     * Total aggregated sum of function builds compute time.
     */
    @SerializedName("buildsTimeTotal")
    val buildsTimeTotal: Long,

    /**
     * Total aggregated number of function builds.
     */
    @SerializedName("buildsTotal")
    val buildsTotal: Long,

    /**
     * Aggregated number of function deployments per period.
     */
    @SerializedName("deployments")
    val deployments: List<Metric>,

    /**
     * Aggregated number of  function deployments storage per period.
     */
    @SerializedName("deploymentsStorage")
    val deploymentsStorage: List<Metric>,

    /**
     * Total aggregated sum of function deployments storage.
     */
    @SerializedName("deploymentsStorageTotal")
    val deploymentsStorageTotal: Long,

    /**
     * Total aggregated number of function deployments.
     */
    @SerializedName("deploymentsTotal")
    val deploymentsTotal: Long,

    /**
     * Aggregated number of function executions per period.
     */
    @SerializedName("executions")
    val executions: List<Metric>,

    /**
     * Aggregated number of function mbSeconds per period.
     */
    @SerializedName("executionsMbSeconds")
    val executionsMbSeconds: List<Metric>,

    /**
     * Total aggregated sum of function executions mbSeconds.
     */
    @SerializedName("executionsMbSecondsTotal")
    val executionsMbSecondsTotal: Long,

    /**
     * Aggregated number of function executions compute time per period.
     */
    @SerializedName("executionsTime")
    val executionsTime: List<Metric>,

    /**
     * Total aggregated sum of function  executions compute time.
     */
    @SerializedName("executionsTimeTotal")
    val executionsTimeTotal: Long,

    /**
     * Total  aggregated number of function executions.
     */
    @SerializedName("executionsTotal")
    val executionsTotal: Long,

    /**
     * The time range of the usage stats.
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
        "buildsTimeAverage" to buildsTimeAverage as Any,
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
        "range" to range as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = UsageFunction(
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
            buildsTimeAverage = (map["buildsTimeAverage"] as Number).toLong(),
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
            range = map["range"] as String,
        )
    }
}