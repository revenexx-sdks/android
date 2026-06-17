package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * MFAFactors
 */
data class MfaFactors(
    /**
     * Can email be used for MFA challenge for this account.
     */
    @SerializedName("email")
    val email: Boolean,

    /**
     * Can phone (SMS) be used for MFA challenge for this account.
     */
    @SerializedName("phone")
    val phone: Boolean,

    /**
     * Can recovery code be used for MFA challenge for this account.
     */
    @SerializedName("recoveryCode")
    val recoveryCode: Boolean,

    /**
     * Can TOTP be used for MFA challenge for this account.
     */
    @SerializedName("totp")
    val totp: Boolean,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "phone" to phone as Any,
        "recoveryCode" to recoveryCode as Any,
        "totp" to totp as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MfaFactors(
            email = map["email"] as Boolean,
            phone = map["phone"] as Boolean,
            recoveryCode = map["recoveryCode"] as Boolean,
            totp = map["totp"] as Boolean,
        )
    }
}