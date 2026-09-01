package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketReadinessCheckId
import com.revenexx.enums.MarketReadinessSeverity

/**
 * One question asked of the market, its verdict, and how much the answer costs.
 */
data class MarketReadinessCheck(
    /**
     * One sentence naming what was found and, for a warning, what covers for it.
     */
    @SerializedName("detail")
    var detail: String?,

    /**
     * Which question. 'locales' — is there a language to render in? 'currencies' — is the base currency registered and marked default? 'tax_classes' — is there a rate to tax with? 'tax_basis' — informational, restating whether stored prices are gross or net.
     */
    @SerializedName("id")
    var id: MarketReadinessCheckId?,

    /**
     * Whether this check passed. A false with severity `info` cannot occur — the informational check always passes.
     */
    @SerializedName("ok")
    var ok: Boolean?,

    /**
     * What a failure costs. 'blocking' — the market cannot trade. 'warning' — degraded but serviceable, and `detail` names what covers for it. 'info' — a fact worth reporting that is never a failure. The severity is not fixed per check: no locales is blocking without a tenant fallback_locale and a warning with one.
     */
    @SerializedName("severity")
    var severity: MarketReadinessSeverity?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "detail" to detail as Any,
        "id" to id?.value as Any,
        "ok" to ok as Any,
        "severity" to severity?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketReadinessCheck(
            detail = map["detail"] as? String,
            id = MarketReadinessCheckId.values().find { it.value == (map["id"] as? String) } ?: null,
            ok = map["ok"] as? Boolean,
            severity = MarketReadinessSeverity.values().find { it.value == (map["severity"] as? String) } ?: null,
        )
    }
}