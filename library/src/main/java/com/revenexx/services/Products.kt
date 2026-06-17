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
class Products(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsList(
    ): Any {
        val apiPath = "/v1/products"

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
     * @param sku 
     * @param attributeValues 
     * @param completeness 
     * @param deletedAt 
     * @param enabled 
     * @param familyId 
     * @param familyVariantId 
     * @param kind 
     * @param parentId 
     * @param quantifiedAssociations 
     * @param taxClass 
     * @return [com.revenexx.models.Products]
     */
    @JvmOverloads
    suspend fun productsCreate(
        sku: String,
        attributeValues: Any? = null,
        completeness: Any? = null,
        deletedAt: String? = null,
        enabled: Boolean? = null,
        familyId: String? = null,
        familyVariantId: String? = null,
        kind: String? = null,
        parentId: String? = null,
        quantifiedAssociations: Any? = null,
        taxClass: String? = null,
    ): com.revenexx.models.Products {
        val apiPath = "/v1/products"

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_values" to attributeValues,
            "completeness" to completeness,
            "deleted_at" to deletedAt,
            "enabled" to enabled,
            "family_id" to familyId,
            "family_variant_id" to familyVariantId,
            "kind" to kind,
            "parent_id" to parentId,
            "quantified_associations" to quantifiedAssociations,
            "sku" to sku,
            "tax_class" to taxClass,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Products = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Products.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Products::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsAssetFamiliesList(
    ): Any {
        val apiPath = "/v1/products/asset_families"

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
     * @param code 
     * @param labels 
     * @param namingConvention 
     * @return [com.revenexx.models.AssetFamilies]
     */
    @JvmOverloads
    suspend fun productsAssetFamiliesCreate(
        code: String,
        labels: Any? = null,
        namingConvention: Any? = null,
    ): com.revenexx.models.AssetFamilies {
        val apiPath = "/v1/products/asset_families"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "naming_convention" to namingConvention,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AssetFamilies = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AssetFamilies.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AssetFamilies::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsAssetFamiliesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/asset_families/{id}"
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
     * @return [com.revenexx.models.AssetFamilies]
     */
    suspend fun productsAssetFamiliesGet(
        id: String,
    ): com.revenexx.models.AssetFamilies {
        val apiPath = "/v1/products/asset_families/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.AssetFamilies = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AssetFamilies.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AssetFamilies::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param labels 
     * @param namingConvention 
     * @return [com.revenexx.models.AssetFamilies]
     */
    @JvmOverloads
    suspend fun productsAssetFamiliesUpdate(
        id: String,
        code: String? = null,
        labels: Any? = null,
        namingConvention: Any? = null,
    ): com.revenexx.models.AssetFamilies {
        val apiPath = "/v1/products/asset_families/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "naming_convention" to namingConvention,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AssetFamilies = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AssetFamilies.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AssetFamilies::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsAssetsList(
    ): Any {
        val apiPath = "/v1/products/assets"

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
     * @param assetFamilyId 
     * @param code 
     * @param attributeValues 
     * @param mediaUuid 
     * @return [com.revenexx.models.Assets]
     */
    @JvmOverloads
    suspend fun productsAssetsCreate(
        assetFamilyId: String,
        code: String,
        attributeValues: Any? = null,
        mediaUuid: String? = null,
    ): com.revenexx.models.Assets {
        val apiPath = "/v1/products/assets"

        val apiParams = mutableMapOf<String, Any?>(
            "asset_family_id" to assetFamilyId,
            "attribute_values" to attributeValues,
            "code" to code,
            "media_uuid" to mediaUuid,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Assets = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Assets.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Assets::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsAssetsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/assets/{id}"
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
     * @return [com.revenexx.models.Assets]
     */
    suspend fun productsAssetsGet(
        id: String,
    ): com.revenexx.models.Assets {
        val apiPath = "/v1/products/assets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Assets = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Assets.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Assets::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param assetFamilyId 
     * @param attributeValues 
     * @param code 
     * @param mediaUuid 
     * @return [com.revenexx.models.Assets]
     */
    @JvmOverloads
    suspend fun productsAssetsUpdate(
        id: String,
        assetFamilyId: String? = null,
        attributeValues: Any? = null,
        code: String? = null,
        mediaUuid: String? = null,
    ): com.revenexx.models.Assets {
        val apiPath = "/v1/products/assets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "asset_family_id" to assetFamilyId,
            "attribute_values" to attributeValues,
            "code" to code,
            "media_uuid" to mediaUuid,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Assets = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Assets.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Assets::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsAssociationTypesList(
    ): Any {
        val apiPath = "/v1/products/association_types"

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
     * @param code 
     * @param isQuantified 
     * @param isTwoWay 
     * @param labels 
     * @return [com.revenexx.models.AssociationTypes]
     */
    @JvmOverloads
    suspend fun productsAssociationTypesCreate(
        code: String,
        isQuantified: Boolean? = null,
        isTwoWay: Boolean? = null,
        labels: Any? = null,
    ): com.revenexx.models.AssociationTypes {
        val apiPath = "/v1/products/association_types"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_quantified" to isQuantified,
            "is_two_way" to isTwoWay,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AssociationTypes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AssociationTypes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AssociationTypes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsAssociationTypesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/association_types/{id}"
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
     * @return [com.revenexx.models.AssociationTypes]
     */
    suspend fun productsAssociationTypesGet(
        id: String,
    ): com.revenexx.models.AssociationTypes {
        val apiPath = "/v1/products/association_types/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.AssociationTypes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AssociationTypes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AssociationTypes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param isQuantified 
     * @param isTwoWay 
     * @param labels 
     * @return [com.revenexx.models.AssociationTypes]
     */
    @JvmOverloads
    suspend fun productsAssociationTypesUpdate(
        id: String,
        code: String? = null,
        isQuantified: Boolean? = null,
        isTwoWay: Boolean? = null,
        labels: Any? = null,
    ): com.revenexx.models.AssociationTypes {
        val apiPath = "/v1/products/association_types/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_quantified" to isQuantified,
            "is_two_way" to isTwoWay,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AssociationTypes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AssociationTypes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AssociationTypes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsAttributeGroupsList(
    ): Any {
        val apiPath = "/v1/products/attribute_groups"

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
     * @param code 
     * @param labels 
     * @param position 
     * @return [com.revenexx.models.AttributeGroups]
     */
    @JvmOverloads
    suspend fun productsAttributeGroupsCreate(
        code: String,
        labels: Any? = null,
        position: Long? = null,
    ): com.revenexx.models.AttributeGroups {
        val apiPath = "/v1/products/attribute_groups"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "position" to position,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AttributeGroups = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AttributeGroups.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AttributeGroups::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsAttributeGroupsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/attribute_groups/{id}"
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
     * @return [com.revenexx.models.AttributeGroups]
     */
    suspend fun productsAttributeGroupsGet(
        id: String,
    ): com.revenexx.models.AttributeGroups {
        val apiPath = "/v1/products/attribute_groups/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.AttributeGroups = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AttributeGroups.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AttributeGroups::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param labels 
     * @param position 
     * @return [com.revenexx.models.AttributeGroups]
     */
    @JvmOverloads
    suspend fun productsAttributeGroupsUpdate(
        id: String,
        code: String? = null,
        labels: Any? = null,
        position: Long? = null,
    ): com.revenexx.models.AttributeGroups {
        val apiPath = "/v1/products/attribute_groups/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "position" to position,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AttributeGroups = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AttributeGroups.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AttributeGroups::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsAttributeOptionsList(
    ): Any {
        val apiPath = "/v1/products/attribute_options"

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
     * @param attributeId 
     * @param code 
     * @param labels 
     * @param position 
     * @param swatch 
     * @return [com.revenexx.models.AttributeOptions]
     */
    @JvmOverloads
    suspend fun productsAttributeOptionsCreate(
        attributeId: String,
        code: String,
        labels: Any? = null,
        position: Long? = null,
        swatch: Any? = null,
    ): com.revenexx.models.AttributeOptions {
        val apiPath = "/v1/products/attribute_options"

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_id" to attributeId,
            "code" to code,
            "labels" to labels,
            "position" to position,
            "swatch" to swatch,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AttributeOptions = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AttributeOptions.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AttributeOptions::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsAttributeOptionsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/attribute_options/{id}"
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
     * @return [com.revenexx.models.AttributeOptions]
     */
    suspend fun productsAttributeOptionsGet(
        id: String,
    ): com.revenexx.models.AttributeOptions {
        val apiPath = "/v1/products/attribute_options/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.AttributeOptions = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AttributeOptions.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AttributeOptions::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param attributeId 
     * @param code 
     * @param labels 
     * @param position 
     * @param swatch 
     * @return [com.revenexx.models.AttributeOptions]
     */
    @JvmOverloads
    suspend fun productsAttributeOptionsUpdate(
        id: String,
        attributeId: String? = null,
        code: String? = null,
        labels: Any? = null,
        position: Long? = null,
        swatch: Any? = null,
    ): com.revenexx.models.AttributeOptions {
        val apiPath = "/v1/products/attribute_options/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_id" to attributeId,
            "code" to code,
            "labels" to labels,
            "position" to position,
            "swatch" to swatch,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AttributeOptions = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AttributeOptions.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AttributeOptions::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsAttributesList(
    ): Any {
        val apiPath = "/v1/products/attributes"

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
     * @param code 
     * @param type 
     * @param config 
     * @param entityRef 
     * @param entityType 
     * @param groupId 
     * @param isFilterable 
     * @param isUnique 
     * @param labels 
     * @param localizable 
     * @param position 
     * @param scopable 
     * @param usableInGrid 
     * @param validation 
     * @return [com.revenexx.models.Attributes]
     */
    @JvmOverloads
    suspend fun productsAttributesCreate(
        code: String,
        type: String,
        config: Any? = null,
        entityRef: String? = null,
        entityType: String? = null,
        groupId: String? = null,
        isFilterable: Boolean? = null,
        isUnique: Boolean? = null,
        labels: Any? = null,
        localizable: Boolean? = null,
        position: Long? = null,
        scopable: Boolean? = null,
        usableInGrid: Boolean? = null,
        validation: Any? = null,
    ): com.revenexx.models.Attributes {
        val apiPath = "/v1/products/attributes"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "config" to config,
            "entity_ref" to entityRef,
            "entity_type" to entityType,
            "group_id" to groupId,
            "is_filterable" to isFilterable,
            "is_unique" to isUnique,
            "labels" to labels,
            "localizable" to localizable,
            "position" to position,
            "scopable" to scopable,
            "type" to type,
            "usable_in_grid" to usableInGrid,
            "validation" to validation,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Attributes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Attributes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Attributes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsAttributesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/attributes/{id}"
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
     * @return [com.revenexx.models.Attributes]
     */
    suspend fun productsAttributesGet(
        id: String,
    ): com.revenexx.models.Attributes {
        val apiPath = "/v1/products/attributes/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Attributes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Attributes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Attributes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param config 
     * @param entityRef 
     * @param entityType 
     * @param groupId 
     * @param isFilterable 
     * @param isUnique 
     * @param labels 
     * @param localizable 
     * @param position 
     * @param scopable 
     * @param type 
     * @param usableInGrid 
     * @param validation 
     * @return [com.revenexx.models.Attributes]
     */
    @JvmOverloads
    suspend fun productsAttributesUpdate(
        id: String,
        code: String? = null,
        config: Any? = null,
        entityRef: String? = null,
        entityType: String? = null,
        groupId: String? = null,
        isFilterable: Boolean? = null,
        isUnique: Boolean? = null,
        labels: Any? = null,
        localizable: Boolean? = null,
        position: Long? = null,
        scopable: Boolean? = null,
        type: String? = null,
        usableInGrid: Boolean? = null,
        validation: Any? = null,
    ): com.revenexx.models.Attributes {
        val apiPath = "/v1/products/attributes/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "config" to config,
            "entity_ref" to entityRef,
            "entity_type" to entityType,
            "group_id" to groupId,
            "is_filterable" to isFilterable,
            "is_unique" to isUnique,
            "labels" to labels,
            "localizable" to localizable,
            "position" to position,
            "scopable" to scopable,
            "type" to type,
            "usable_in_grid" to usableInGrid,
            "validation" to validation,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Attributes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Attributes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Attributes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param ids 
     * @param skus 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun productsBatch(
        ids: List<String>? = null,
        skus: List<String>? = null,
    ): Any {
        val apiPath = "/v1/products/batch"

        val apiParams = mutableMapOf<String, Any?>(
            "ids" to ids,
            "skus" to skus,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsCategoriesList(
    ): Any {
        val apiPath = "/v1/products/categories"

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
     * @param code 
     * @param labels 
     * @param parentId 
     * @param path 
     * @param position 
     * @param values 
     * @return [com.revenexx.models.Categories]
     */
    @JvmOverloads
    suspend fun productsCategoriesCreate(
        code: String,
        labels: Any? = null,
        parentId: String? = null,
        path: String? = null,
        position: Long? = null,
        values: Any? = null,
    ): com.revenexx.models.Categories {
        val apiPath = "/v1/products/categories"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "parent_id" to parentId,
            "path" to path,
            "position" to position,
            "values" to values,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Categories = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Categories.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Categories::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsCategoriesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/categories/{id}"
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
     * @return [com.revenexx.models.Categories]
     */
    suspend fun productsCategoriesGet(
        id: String,
    ): com.revenexx.models.Categories {
        val apiPath = "/v1/products/categories/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Categories = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Categories.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Categories::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param labels 
     * @param parentId 
     * @param path 
     * @param position 
     * @param values 
     * @return [com.revenexx.models.Categories]
     */
    @JvmOverloads
    suspend fun productsCategoriesUpdate(
        id: String,
        code: String? = null,
        labels: Any? = null,
        parentId: String? = null,
        path: String? = null,
        position: Long? = null,
        values: Any? = null,
    ): com.revenexx.models.Categories {
        val apiPath = "/v1/products/categories/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "parent_id" to parentId,
            "path" to path,
            "position" to position,
            "values" to values,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Categories = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Categories.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Categories::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsFamiliesList(
    ): Any {
        val apiPath = "/v1/products/families"

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
     * @param code 
     * @param imageAttribute 
     * @param labelAttribute 
     * @param labels 
     * @return [com.revenexx.models.Families]
     */
    @JvmOverloads
    suspend fun productsFamiliesCreate(
        code: String,
        imageAttribute: String? = null,
        labelAttribute: String? = null,
        labels: Any? = null,
    ): com.revenexx.models.Families {
        val apiPath = "/v1/products/families"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "image_attribute" to imageAttribute,
            "label_attribute" to labelAttribute,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Families = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Families.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Families::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsFamiliesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/families/{id}"
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
     * @return [com.revenexx.models.Families]
     */
    suspend fun productsFamiliesGet(
        id: String,
    ): com.revenexx.models.Families {
        val apiPath = "/v1/products/families/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Families = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Families.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Families::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param imageAttribute 
     * @param labelAttribute 
     * @param labels 
     * @return [com.revenexx.models.Families]
     */
    @JvmOverloads
    suspend fun productsFamiliesUpdate(
        id: String,
        code: String? = null,
        imageAttribute: String? = null,
        labelAttribute: String? = null,
        labels: Any? = null,
    ): com.revenexx.models.Families {
        val apiPath = "/v1/products/families/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "image_attribute" to imageAttribute,
            "label_attribute" to labelAttribute,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Families = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Families.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Families::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsFamilyAttributesList(
    ): Any {
        val apiPath = "/v1/products/family_attributes"

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
     * @param attributeId 
     * @param familyId 
     * @param isRequired 
     * @param position 
     * @param requiredChannels 
     * @return [com.revenexx.models.FamilyAttributes]
     */
    @JvmOverloads
    suspend fun productsFamilyAttributesCreate(
        attributeId: String,
        familyId: String,
        isRequired: Boolean? = null,
        position: Long? = null,
        requiredChannels: Any? = null,
    ): com.revenexx.models.FamilyAttributes {
        val apiPath = "/v1/products/family_attributes"

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_id" to attributeId,
            "family_id" to familyId,
            "is_required" to isRequired,
            "position" to position,
            "required_channels" to requiredChannels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.FamilyAttributes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FamilyAttributes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FamilyAttributes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsFamilyAttributesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/family_attributes/{id}"
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
     * @return [com.revenexx.models.FamilyAttributes]
     */
    suspend fun productsFamilyAttributesGet(
        id: String,
    ): com.revenexx.models.FamilyAttributes {
        val apiPath = "/v1/products/family_attributes/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.FamilyAttributes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FamilyAttributes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FamilyAttributes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param attributeId 
     * @param familyId 
     * @param isRequired 
     * @param position 
     * @param requiredChannels 
     * @return [com.revenexx.models.FamilyAttributes]
     */
    @JvmOverloads
    suspend fun productsFamilyAttributesUpdate(
        id: String,
        attributeId: String? = null,
        familyId: String? = null,
        isRequired: Boolean? = null,
        position: Long? = null,
        requiredChannels: Any? = null,
    ): com.revenexx.models.FamilyAttributes {
        val apiPath = "/v1/products/family_attributes/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_id" to attributeId,
            "family_id" to familyId,
            "is_required" to isRequired,
            "position" to position,
            "required_channels" to requiredChannels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.FamilyAttributes = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FamilyAttributes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FamilyAttributes::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsFamilyVariantsList(
    ): Any {
        val apiPath = "/v1/products/family_variants"

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
     * @param code 
     * @param familyId 
     * @param axes 
     * @param labels 
     * @return [com.revenexx.models.FamilyVariants]
     */
    @JvmOverloads
    suspend fun productsFamilyVariantsCreate(
        code: String,
        familyId: String,
        axes: Any? = null,
        labels: Any? = null,
    ): com.revenexx.models.FamilyVariants {
        val apiPath = "/v1/products/family_variants"

        val apiParams = mutableMapOf<String, Any?>(
            "axes" to axes,
            "code" to code,
            "family_id" to familyId,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.FamilyVariants = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FamilyVariants.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FamilyVariants::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsFamilyVariantsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/family_variants/{id}"
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
     * @return [com.revenexx.models.FamilyVariants]
     */
    suspend fun productsFamilyVariantsGet(
        id: String,
    ): com.revenexx.models.FamilyVariants {
        val apiPath = "/v1/products/family_variants/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.FamilyVariants = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FamilyVariants.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FamilyVariants::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param axes 
     * @param code 
     * @param familyId 
     * @param labels 
     * @return [com.revenexx.models.FamilyVariants]
     */
    @JvmOverloads
    suspend fun productsFamilyVariantsUpdate(
        id: String,
        axes: Any? = null,
        code: String? = null,
        familyId: String? = null,
        labels: Any? = null,
    ): com.revenexx.models.FamilyVariants {
        val apiPath = "/v1/products/family_variants/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "axes" to axes,
            "code" to code,
            "family_id" to familyId,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.FamilyVariants = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FamilyVariants.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FamilyVariants::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsMeasurementFamiliesList(
    ): Any {
        val apiPath = "/v1/products/measurement_families"

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
     * @param code 
     * @param standardUnit 
     * @param labels 
     * @param units 
     * @return [com.revenexx.models.MeasurementFamilies]
     */
    @JvmOverloads
    suspend fun productsMeasurementFamiliesCreate(
        code: String,
        standardUnit: String,
        labels: Any? = null,
        units: Any? = null,
    ): com.revenexx.models.MeasurementFamilies {
        val apiPath = "/v1/products/measurement_families"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "standard_unit" to standardUnit,
            "units" to units,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MeasurementFamilies = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MeasurementFamilies.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MeasurementFamilies::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsMeasurementFamiliesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/measurement_families/{id}"
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
     * @return [com.revenexx.models.MeasurementFamilies]
     */
    suspend fun productsMeasurementFamiliesGet(
        id: String,
    ): com.revenexx.models.MeasurementFamilies {
        val apiPath = "/v1/products/measurement_families/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MeasurementFamilies = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MeasurementFamilies.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MeasurementFamilies::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param labels 
     * @param standardUnit 
     * @param units 
     * @return [com.revenexx.models.MeasurementFamilies]
     */
    @JvmOverloads
    suspend fun productsMeasurementFamiliesUpdate(
        id: String,
        code: String? = null,
        labels: Any? = null,
        standardUnit: String? = null,
        units: Any? = null,
    ): com.revenexx.models.MeasurementFamilies {
        val apiPath = "/v1/products/measurement_families/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "standard_unit" to standardUnit,
            "units" to units,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MeasurementFamilies = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MeasurementFamilies.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MeasurementFamilies::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsProductAssociationsList(
    ): Any {
        val apiPath = "/v1/products/product_associations"

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
     * @param associationTypeId 
     * @param productId 
     * @param targetProductId 
     * @param position 
     * @param quantity 
     * @return [com.revenexx.models.ProductAssociations]
     */
    @JvmOverloads
    suspend fun productsProductAssociationsCreate(
        associationTypeId: String,
        productId: String,
        targetProductId: String,
        position: Long? = null,
        quantity: Double? = null,
    ): com.revenexx.models.ProductAssociations {
        val apiPath = "/v1/products/product_associations"

        val apiParams = mutableMapOf<String, Any?>(
            "association_type_id" to associationTypeId,
            "position" to position,
            "product_id" to productId,
            "quantity" to quantity,
            "target_product_id" to targetProductId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ProductAssociations = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProductAssociations.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProductAssociations::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsProductAssociationsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/product_associations/{id}"
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
     * @return [com.revenexx.models.ProductAssociations]
     */
    suspend fun productsProductAssociationsGet(
        id: String,
    ): com.revenexx.models.ProductAssociations {
        val apiPath = "/v1/products/product_associations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ProductAssociations = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProductAssociations.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProductAssociations::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param associationTypeId 
     * @param position 
     * @param productId 
     * @param quantity 
     * @param targetProductId 
     * @return [com.revenexx.models.ProductAssociations]
     */
    @JvmOverloads
    suspend fun productsProductAssociationsUpdate(
        id: String,
        associationTypeId: String? = null,
        position: Long? = null,
        productId: String? = null,
        quantity: Double? = null,
        targetProductId: String? = null,
    ): com.revenexx.models.ProductAssociations {
        val apiPath = "/v1/products/product_associations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "association_type_id" to associationTypeId,
            "position" to position,
            "product_id" to productId,
            "quantity" to quantity,
            "target_product_id" to targetProductId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ProductAssociations = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProductAssociations.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProductAssociations::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsProductCategoriesList(
    ): Any {
        val apiPath = "/v1/products/product_categories"

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
     * @param categoryId 
     * @param productId 
     * @param position 
     * @return [com.revenexx.models.ProductCategories]
     */
    @JvmOverloads
    suspend fun productsProductCategoriesCreate(
        categoryId: String,
        productId: String,
        position: Long? = null,
    ): com.revenexx.models.ProductCategories {
        val apiPath = "/v1/products/product_categories"

        val apiParams = mutableMapOf<String, Any?>(
            "category_id" to categoryId,
            "position" to position,
            "product_id" to productId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ProductCategories = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProductCategories.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProductCategories::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsProductCategoriesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/product_categories/{id}"
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
     * @return [com.revenexx.models.ProductCategories]
     */
    suspend fun productsProductCategoriesGet(
        id: String,
    ): com.revenexx.models.ProductCategories {
        val apiPath = "/v1/products/product_categories/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ProductCategories = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProductCategories.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProductCategories::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param categoryId 
     * @param position 
     * @param productId 
     * @return [com.revenexx.models.ProductCategories]
     */
    @JvmOverloads
    suspend fun productsProductCategoriesUpdate(
        id: String,
        categoryId: String? = null,
        position: Long? = null,
        productId: String? = null,
    ): com.revenexx.models.ProductCategories {
        val apiPath = "/v1/products/product_categories/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "category_id" to categoryId,
            "position" to position,
            "product_id" to productId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ProductCategories = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProductCategories.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProductCategories::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsReferenceEntitiesList(
    ): Any {
        val apiPath = "/v1/products/reference_entities"

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
     * @param code 
     * @param image 
     * @param labels 
     * @return [com.revenexx.models.ReferenceEntities]
     */
    @JvmOverloads
    suspend fun productsReferenceEntitiesCreate(
        code: String,
        image: String? = null,
        labels: Any? = null,
    ): com.revenexx.models.ReferenceEntities {
        val apiPath = "/v1/products/reference_entities"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "image" to image,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ReferenceEntities = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReferenceEntities.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReferenceEntities::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsReferenceEntitiesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/reference_entities/{id}"
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
     * @return [com.revenexx.models.ReferenceEntities]
     */
    suspend fun productsReferenceEntitiesGet(
        id: String,
    ): com.revenexx.models.ReferenceEntities {
        val apiPath = "/v1/products/reference_entities/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ReferenceEntities = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReferenceEntities.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReferenceEntities::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code 
     * @param image 
     * @param labels 
     * @return [com.revenexx.models.ReferenceEntities]
     */
    @JvmOverloads
    suspend fun productsReferenceEntitiesUpdate(
        id: String,
        code: String? = null,
        image: String? = null,
        labels: Any? = null,
    ): com.revenexx.models.ReferenceEntities {
        val apiPath = "/v1/products/reference_entities/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "image" to image,
            "labels" to labels,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ReferenceEntities = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReferenceEntities.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReferenceEntities::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun productsReferenceEntityRecordsList(
    ): Any {
        val apiPath = "/v1/products/reference_entity_records"

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
     * @param code 
     * @param referenceEntityId 
     * @param attributeValues 
     * @param labels 
     * @return [com.revenexx.models.ReferenceEntityRecords]
     */
    @JvmOverloads
    suspend fun productsReferenceEntityRecordsCreate(
        code: String,
        referenceEntityId: String,
        attributeValues: Any? = null,
        labels: Any? = null,
    ): com.revenexx.models.ReferenceEntityRecords {
        val apiPath = "/v1/products/reference_entity_records"

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_values" to attributeValues,
            "code" to code,
            "labels" to labels,
            "reference_entity_id" to referenceEntityId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ReferenceEntityRecords = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReferenceEntityRecords.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReferenceEntityRecords::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsReferenceEntityRecordsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/reference_entity_records/{id}"
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
     * @return [com.revenexx.models.ReferenceEntityRecords]
     */
    suspend fun productsReferenceEntityRecordsGet(
        id: String,
    ): com.revenexx.models.ReferenceEntityRecords {
        val apiPath = "/v1/products/reference_entity_records/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ReferenceEntityRecords = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReferenceEntityRecords.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReferenceEntityRecords::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param attributeValues 
     * @param code 
     * @param labels 
     * @param referenceEntityId 
     * @return [com.revenexx.models.ReferenceEntityRecords]
     */
    @JvmOverloads
    suspend fun productsReferenceEntityRecordsUpdate(
        id: String,
        attributeValues: Any? = null,
        code: String? = null,
        labels: Any? = null,
        referenceEntityId: String? = null,
    ): com.revenexx.models.ReferenceEntityRecords {
        val apiPath = "/v1/products/reference_entity_records/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_values" to attributeValues,
            "code" to code,
            "labels" to labels,
            "reference_entity_id" to referenceEntityId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ReferenceEntityRecords = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReferenceEntityRecords.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReferenceEntityRecords::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun productsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/products/{id}"
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
     * @return [com.revenexx.models.Products]
     */
    suspend fun productsGet(
        id: String,
    ): com.revenexx.models.Products {
        val apiPath = "/v1/products/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Products = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Products.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Products::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param attributeValues 
     * @param completeness 
     * @param deletedAt 
     * @param enabled 
     * @param familyId 
     * @param familyVariantId 
     * @param kind 
     * @param parentId 
     * @param quantifiedAssociations 
     * @param sku 
     * @param taxClass 
     * @return [com.revenexx.models.Products]
     */
    @JvmOverloads
    suspend fun productsUpdate(
        id: String,
        attributeValues: Any? = null,
        completeness: Any? = null,
        deletedAt: String? = null,
        enabled: Boolean? = null,
        familyId: String? = null,
        familyVariantId: String? = null,
        kind: String? = null,
        parentId: String? = null,
        quantifiedAssociations: Any? = null,
        sku: String? = null,
        taxClass: String? = null,
    ): com.revenexx.models.Products {
        val apiPath = "/v1/products/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "attribute_values" to attributeValues,
            "completeness" to completeness,
            "deleted_at" to deletedAt,
            "enabled" to enabled,
            "family_id" to familyId,
            "family_variant_id" to familyVariantId,
            "kind" to kind,
            "parent_id" to parentId,
            "quantified_associations" to quantifiedAssociations,
            "sku" to sku,
            "tax_class" to taxClass,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Products = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Products.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Products::class.java,
            converter,
        )
    }


}