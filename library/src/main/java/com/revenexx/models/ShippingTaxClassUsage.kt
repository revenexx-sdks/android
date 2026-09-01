package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What in this app still points at a market tax class, by code.
 */
data class ShippingTaxClassUsage(
    /**
     * The tax-class code that was asked about, echoed back.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * True when this market's shipping_tax_class setting names the code — the class every method that names none falls back to.
     */
    @SerializedName("fallback_setting")
    var fallback_setting: Boolean?,

    /**
     * True when at least one method or the market fallback setting names it. The single field a caller deciding whether to allow a delete needs; the rest is so it can word the refusal.
     */
    @SerializedName("in_use")
    var in_use: Boolean?,

    /**
     * The first 20 of them, so a refusal can name names instead of a number.
     */
    @SerializedName("methods")
    var methods: List<Any>?,

    /**
     * How many methods name this code as their own tax_class. Capped at 500 — a tenant with more shipping methods than that has a bigger problem than an imprecise count.
     */
    @SerializedName("shipping_methods")
    var shipping_methods: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "fallback_setting" to fallback_setting as Any,
        "in_use" to in_use as Any,
        "methods" to methods as Any,
        "shipping_methods" to shipping_methods as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingTaxClassUsage(
            code = map["code"] as? String,
            fallback_setting = map["fallback_setting"] as? Boolean,
            in_use = map["in_use"] as? Boolean,
            methods = map["methods"] as? List<Any>,
            shipping_methods = (map["shipping_methods"] as? Number)?.toLong(),
        )
    }
}