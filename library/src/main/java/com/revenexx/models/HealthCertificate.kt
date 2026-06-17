package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Health Certificate
 */
data class HealthCertificate(
    /**
     * Issuer organisation
     */
    @SerializedName("issuerOrganisation")
    val issuerOrganisation: String,

    /**
     * Certificate name
     */
    @SerializedName("name")
    val name: String,

    /**
     * Signature type SN
     */
    @SerializedName("signatureTypeSN")
    val signatureTypeSN: String,

    /**
     * Subject SN
     */
    @SerializedName("subjectSN")
    val subjectSN: String,

    /**
     * Valid from
     */
    @SerializedName("validFrom")
    val validFrom: String,

    /**
     * Valid to
     */
    @SerializedName("validTo")
    val validTo: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "issuerOrganisation" to issuerOrganisation as Any,
        "name" to name as Any,
        "signatureTypeSN" to signatureTypeSN as Any,
        "subjectSN" to subjectSN as Any,
        "validFrom" to validFrom as Any,
        "validTo" to validTo as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = HealthCertificate(
            issuerOrganisation = map["issuerOrganisation"] as String,
            name = map["name"] as String,
            signatureTypeSN = map["signatureTypeSN"] as String,
            subjectSN = map["subjectSN"] as String,
            validFrom = map["validFrom"] as String,
            validTo = map["validTo"] as String,
        )
    }
}