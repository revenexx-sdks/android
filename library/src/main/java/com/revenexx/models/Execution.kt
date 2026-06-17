package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ExecutionStatus
import com.revenexx.enums.ExecutionTrigger

/**
 * Execution
 */
data class Execution(
    /**
     * Execution creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Execution ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Execution roles.
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * Execution update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Function's deployment ID used to create the execution.
     */
    @SerializedName("deploymentId")
    val deploymentId: String,

    /**
     * Resource(function/site) execution duration in seconds.
     */
    @SerializedName("duration")
    val duration: Double,

    /**
     * Function errors. Includes the last 4,000 characters. This will return an empty string unless the response is returned using an API key or as part of a webhook payload.
     */
    @SerializedName("errors")
    val errors: String,

    /**
     * Function ID.
     */
    @SerializedName("functionId")
    val functionId: String,

    /**
     * Function logs. Includes the last 4,000 characters. This will return an empty string unless the response is returned using an API key or as part of a webhook payload.
     */
    @SerializedName("logs")
    val logs: String,

    /**
     * HTTP request headers as a key-value object. This will return only whitelisted headers. All headers are returned if execution is created as synchronous.
     */
    @SerializedName("requestHeaders")
    val requestHeaders: List<Headers>,

    /**
     * HTTP request method type.
     */
    @SerializedName("requestMethod")
    val requestMethod: String,

    /**
     * HTTP request path and query.
     */
    @SerializedName("requestPath")
    val requestPath: String,

    /**
     * HTTP response body. This will return empty unless execution is created as synchronous.
     */
    @SerializedName("responseBody")
    val responseBody: String,

    /**
     * HTTP response headers as a key-value object. This will return only whitelisted headers. All headers are returned if execution is created as synchronous.
     */
    @SerializedName("responseHeaders")
    val responseHeaders: List<Headers>,

    /**
     * HTTP response status code.
     */
    @SerializedName("responseStatusCode")
    val responseStatusCode: Long,

    /**
     * The scheduled time for execution. If left empty, execution will be queued immediately.
     */
    @SerializedName("scheduledAt")
    var scheduledAt: String?,

    /**
     * The status of the function execution. Possible values can be: `waiting`, `processing`, `completed`, `failed`, or `scheduled`.
     */
    @SerializedName("status")
    val status: ExecutionStatus,

    /**
     * The trigger that caused the function to execute. Possible values can be: `http`, `schedule`, or `event`.
     */
    @SerializedName("trigger")
    val trigger: ExecutionTrigger,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$updatedAt" to updatedAt as Any,
        "deploymentId" to deploymentId as Any,
        "duration" to duration as Any,
        "errors" to errors as Any,
        "functionId" to functionId as Any,
        "logs" to logs as Any,
        "requestHeaders" to requestHeaders.map { it.toMap() } as Any,
        "requestMethod" to requestMethod as Any,
        "requestPath" to requestPath as Any,
        "responseBody" to responseBody as Any,
        "responseHeaders" to responseHeaders.map { it.toMap() } as Any,
        "responseStatusCode" to responseStatusCode as Any,
        "scheduledAt" to scheduledAt as Any,
        "status" to status.value as Any,
        "trigger" to trigger.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Execution(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            updatedAt = map["\$updatedAt"] as String,
            deploymentId = map["deploymentId"] as String,
            duration = (map["duration"] as Number).toDouble(),
            errors = map["errors"] as String,
            functionId = map["functionId"] as String,
            logs = map["logs"] as String,
            requestHeaders = (map["requestHeaders"] as List<Map<String, Any>>).map { Headers.from(map = it) },
            requestMethod = map["requestMethod"] as String,
            requestPath = map["requestPath"] as String,
            responseBody = map["responseBody"] as String,
            responseHeaders = (map["responseHeaders"] as List<Map<String, Any>>).map { Headers.from(map = it) },
            responseStatusCode = (map["responseStatusCode"] as Number).toLong(),
            scheduledAt = map["scheduledAt"] as? String,
            status = ExecutionStatus.values().find { it.value == map["status"] as String }!!,
            trigger = ExecutionTrigger.values().find { it.value == map["trigger"] as String }!!,
        )
    }
}