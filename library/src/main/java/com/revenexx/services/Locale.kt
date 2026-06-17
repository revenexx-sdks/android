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
 * Localisation reference data: countries, currencies, languages.
 */
class Locale(client: Client) : Service(client) {

    /**
     * Get the current user location based on IP. Returns an object with user country code, country name, continent name, continent code, ip address and suggested currency. You can use the locale header to get the data in a supported language.
     * 
     * ([IP Geolocation by DB-IP](https://db-ip.com))
     *
     * @return [com.revenexx.models.Locale]
     */
    suspend fun localeGet(
    ): com.revenexx.models.Locale {
        val apiPath = "/v1/locale"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Locale = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Locale.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Locale::class.java,
            converter,
        )
    }


    /**
     * List of all locale codes in [ISO 639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes).
     *
     * @return [com.revenexx.models.LocaleCodeList]
     */
    suspend fun localeListCodes(
    ): com.revenexx.models.LocaleCodeList {
        val apiPath = "/v1/locale/codes"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LocaleCodeList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LocaleCodeList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LocaleCodeList::class.java,
            converter,
        )
    }


    /**
     * List of all continents. You can use the locale header to get the data in a supported language.
     *
     * @return [com.revenexx.models.ContinentList]
     */
    suspend fun localeListContinents(
    ): com.revenexx.models.ContinentList {
        val apiPath = "/v1/locale/continents"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ContinentList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ContinentList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ContinentList::class.java,
            converter,
        )
    }


    /**
     * List of all countries. You can use the locale header to get the data in a supported language.
     *
     * @return [com.revenexx.models.CountryList]
     */
    suspend fun localeListCountries(
    ): com.revenexx.models.CountryList {
        val apiPath = "/v1/locale/countries"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.CountryList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.CountryList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.CountryList::class.java,
            converter,
        )
    }


    /**
     * List of all countries that are currently members of the EU. You can use the locale header to get the data in a supported language.
     *
     * @return [com.revenexx.models.CountryList]
     */
    suspend fun localeListCountriesEU(
    ): com.revenexx.models.CountryList {
        val apiPath = "/v1/locale/countries/eu"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.CountryList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.CountryList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.CountryList::class.java,
            converter,
        )
    }


    /**
     * List of all countries phone codes. You can use the locale header to get the data in a supported language.
     *
     * @return [com.revenexx.models.PhoneList]
     */
    suspend fun localeListCountriesPhones(
    ): com.revenexx.models.PhoneList {
        val apiPath = "/v1/locale/countries/phones"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PhoneList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PhoneList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PhoneList::class.java,
            converter,
        )
    }


    /**
     * List of all currencies, including currency symbol, name, plural, and decimal digits for all major and minor currencies. You can use the locale header to get the data in a supported language.
     *
     * @return [com.revenexx.models.CurrencyList]
     */
    suspend fun localeListCurrencies(
    ): com.revenexx.models.CurrencyList {
        val apiPath = "/v1/locale/currencies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.CurrencyList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.CurrencyList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.CurrencyList::class.java,
            converter,
        )
    }


    /**
     * List of all languages classified by ISO 639-1 including 2-letter code, name in English, and name in the respective language.
     *
     * @return [com.revenexx.models.LanguageList]
     */
    suspend fun localeListLanguages(
    ): com.revenexx.models.LanguageList {
        val apiPath = "/v1/locale/languages"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LanguageList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LanguageList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LanguageList::class.java,
            converter,
        )
    }


}