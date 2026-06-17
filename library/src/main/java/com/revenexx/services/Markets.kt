package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxAPIRevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * 
 */
class Markets(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun marketsList(
    ): Any {
        val apiPath = "/v1/markets"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param code Market code (unique per tenant).
     * @param name 
     * @param currency ISO 4217 code (default 'EUR').
     * @param isDefault 
     * @param labels Localized display names ({locale: label}).
     * @param position Sort position (default 0).
     * @param status Default 'active'.
     * @return [com.revenexx.models.Market]
     */
    @JvmOverloads
    suspend fun marketsCreate(
        code: String,
        name: String,
        currency: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        status: com.revenexx.enums.MarketStatus? = null,
    ): com.revenexx.models.Market {
        val apiPath = "/v1/markets"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "currency" to currency,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Market = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Market.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Market::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun marketsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/markets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Market]
     */
    suspend fun marketsGet(
        id: String,
    ): com.revenexx.models.Market {
        val apiPath = "/v1/markets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Market = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Market.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Market::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code Market code (unique per tenant).
     * @param currency ISO 4217 code (default 'EUR').
     * @param isDefault 
     * @param labels Localized display names ({locale: label}).
     * @param name 
     * @param position Sort position (default 0).
     * @param status Default 'active'.
     * @return [com.revenexx.models.Market]
     */
    @JvmOverloads
    suspend fun marketsUpdate(
        id: String,
        code: String? = null,
        currency: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        name: String? = null,
        position: Long? = null,
        status: com.revenexx.enums.MarketStatus? = null,
    ): com.revenexx.models.Market {
        val apiPath = "/v1/markets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "currency" to currency,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Market = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Market.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Market::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.MarketContext]
     */
    suspend fun marketsContext(
        id: String,
    ): com.revenexx.models.MarketContext {
        val apiPath = "/v1/markets/{id}/context"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MarketContext = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketContext.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketContext::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @return [Any]
     */
    suspend fun marketsCurrenciesList(
        marketId: String,
    ): Any {
        val apiPath = "/v1/markets/{market_id}/currencies"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param code ISO 4217 code, e.g. EUR (unique per market).
     * @param isDefault 
     * @param position Sort position (default 0).
     * @return [com.revenexx.models.MarketCurrency]
     */
    @JvmOverloads
    suspend fun marketsCurrenciesCreate(
        marketId: String,
        code: String,
        isDefault: Boolean? = null,
        position: Long? = null,
    ): com.revenexx.models.MarketCurrency {
        val apiPath = "/v1/markets/{market_id}/currencies"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "position" to position,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MarketCurrency = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketCurrency.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketCurrency::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @return [Any]
     */
    suspend fun marketsCurrenciesDelete(
        marketId: String,
        id: String,
    ): Any {
        val apiPath = "/v1/markets/{market_id}/currencies/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @return [com.revenexx.models.MarketCurrency]
     */
    suspend fun marketsCurrenciesGet(
        marketId: String,
        id: String,
    ): com.revenexx.models.MarketCurrency {
        val apiPath = "/v1/markets/{market_id}/currencies/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MarketCurrency = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketCurrency.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketCurrency::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @param code ISO 4217 code, e.g. EUR (unique per market).
     * @param isDefault 
     * @param position Sort position (default 0).
     * @return [com.revenexx.models.MarketCurrency]
     */
    @JvmOverloads
    suspend fun marketsCurrenciesUpdate(
        marketId: String,
        id: String,
        code: String? = null,
        isDefault: Boolean? = null,
        position: Long? = null,
    ): com.revenexx.models.MarketCurrency {
        val apiPath = "/v1/markets/{market_id}/currencies/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "position" to position,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MarketCurrency = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketCurrency.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketCurrency::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @return [Any]
     */
    suspend fun marketsLocalesList(
        marketId: String,
    ): Any {
        val apiPath = "/v1/markets/{market_id}/locales"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param code Locale code, e.g. 'de-DE' (unique per market).
     * @param country ISO 3166-1 alpha-2 country code.
     * @param language ISO 639-1 language code.
     * @param isDefault 
     * @param position Sort position (default 0).
     * @return [com.revenexx.models.MarketLocale]
     */
    @JvmOverloads
    suspend fun marketsLocalesCreate(
        marketId: String,
        code: String,
        country: String,
        language: String,
        isDefault: Boolean? = null,
        position: Long? = null,
    ): com.revenexx.models.MarketLocale {
        val apiPath = "/v1/markets/{market_id}/locales"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "country" to country,
            "is_default" to isDefault,
            "language" to language,
            "position" to position,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MarketLocale = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketLocale.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketLocale::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @return [Any]
     */
    suspend fun marketsLocalesDelete(
        marketId: String,
        id: String,
    ): Any {
        val apiPath = "/v1/markets/{market_id}/locales/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @return [com.revenexx.models.MarketLocale]
     */
    suspend fun marketsLocalesGet(
        marketId: String,
        id: String,
    ): com.revenexx.models.MarketLocale {
        val apiPath = "/v1/markets/{market_id}/locales/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MarketLocale = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketLocale.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketLocale::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @param code Locale code, e.g. 'de-DE' (unique per market).
     * @param country ISO 3166-1 alpha-2 country code.
     * @param isDefault 
     * @param language ISO 639-1 language code.
     * @param position Sort position (default 0).
     * @return [com.revenexx.models.MarketLocale]
     */
    @JvmOverloads
    suspend fun marketsLocalesUpdate(
        marketId: String,
        id: String,
        code: String? = null,
        country: String? = null,
        isDefault: Boolean? = null,
        language: String? = null,
        position: Long? = null,
    ): com.revenexx.models.MarketLocale {
        val apiPath = "/v1/markets/{market_id}/locales/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "country" to country,
            "is_default" to isDefault,
            "language" to language,
            "position" to position,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MarketLocale = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketLocale.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketLocale::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @return [Any]
     */
    suspend fun marketsTaxClassesList(
        marketId: String,
    ): Any {
        val apiPath = "/v1/markets/{market_id}/tax_classes"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param code Tax class code (unique per market).
     * @param name 
     * @param isDefault 
     * @param labels Localized display names ({locale: label}).
     * @param position Sort position (default 0).
     * @param rate Tax rate in percent, 0–100 (default 0).
     * @return [com.revenexx.models.MarketTaxClass]
     */
    @JvmOverloads
    suspend fun marketsTaxClassesCreate(
        marketId: String,
        code: String,
        name: String,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        rate: Double? = null,
    ): com.revenexx.models.MarketTaxClass {
        val apiPath = "/v1/markets/{market_id}/tax_classes"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "rate" to rate,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MarketTaxClass = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketTaxClass.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketTaxClass::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @return [Any]
     */
    suspend fun marketsTaxClassesDelete(
        marketId: String,
        id: String,
    ): Any {
        val apiPath = "/v1/markets/{market_id}/tax_classes/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @return [com.revenexx.models.MarketTaxClass]
     */
    suspend fun marketsTaxClassesGet(
        marketId: String,
        id: String,
    ): com.revenexx.models.MarketTaxClass {
        val apiPath = "/v1/markets/{market_id}/tax_classes/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MarketTaxClass = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketTaxClass.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketTaxClass::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param marketId 
     * @param id 
     * @param code Tax class code (unique per market).
     * @param isDefault 
     * @param labels Localized display names ({locale: label}).
     * @param name 
     * @param position Sort position (default 0).
     * @param rate Tax rate in percent, 0–100 (default 0).
     * @return [com.revenexx.models.MarketTaxClass]
     */
    @JvmOverloads
    suspend fun marketsTaxClassesUpdate(
        marketId: String,
        id: String,
        code: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        name: String? = null,
        position: Long? = null,
        rate: Double? = null,
    ): com.revenexx.models.MarketTaxClass {
        val apiPath = "/v1/markets/{market_id}/tax_classes/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "rate" to rate,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MarketTaxClass = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketTaxClass.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketTaxClass::class.java,
            converter,
        )
    }


}