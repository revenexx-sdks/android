package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * What a storefront calls, and the group to start in if you are building a theme. Four read-only routes, no editorial concepts in any of them: resolve one published page by slug or id into a ready-to-render block tree, list the published pages for routing and sitemaps, read the navigation menus, and resolve a share token into the CURRENT unpublished state for a preview link. These serve the published revision — not the live rows — with the requested language filled in from its fallback chain, block-level publish windows applied and library references expanded, so a renderer needs no second call and no knowledge of how any of it was authored.
 */
class PagesDelivery(client: Client) : Service(client) {

    /**
     * One call gives a theme its whole chrome: header, footer and account navigation, each under the key the theme looks it up by. This route reads no filter — fetch all of them once and index by `id`.
     *
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesDeliveryMenus(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/pages/delivery/menus"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
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
     * What a storefront calls to render a URL: `GET /pages/delivery/page?slug=about-us&langcode=de`. Send exactly one selector — `slug` or `id`. `slug` is matched against the page and then against its translations, so a localized URL resolves to its page. Only the PUBLISHED revision is served, so an edit in progress never leaks. What comes back is finished rather than raw: `langcode` is resolved field by field with the page's source language behind it, blocks whose publish window has not opened or has already closed are left out, and every library reference is expanded into the subtree it points at — so a renderer walks the tree it is given and makes no second call for any of it.
     *
     * @param slug The page slug, or the slug of one of its translations, without a leading slash — the path segment the storefront routes. Either this or `id`.
     * @param id The page id, for a storefront that already holds one (from `GET /pages/delivery/pages`). Either this or `slug`.
     * @param langcode Language to resolve the tree for, e.g. `de`. Falls back to the page's source language per field, so a partly translated page still renders whole.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesDeliveryPage(
        slug: String? = null,
        id: String? = null,
        langcode: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/delivery/page"

        val apiParams = mutableMapOf<String, Any?>(
            "slug" to slug,
            "id" to id,
            "langcode" to langcode,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * The route a sitemap, a static build or a link picker is generated from. Only published pages, never a soft-deleted one — `filter` echoes both predicates the route applies on its own. A `?status=` of your own is ignored: this route is the published view by definition.
     *
     * @param limit Page size (default 100, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @param bundle Exact page type — how a theme asks for just its landing pages. The value set belongs to the active theme.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesDeliveryPages(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        bundle: String? = null,
    ): Any {
        val apiPath = "/v1/pages/delivery/pages"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "bundle" to bundle,
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
     * The same shape `GET /pages/delivery/page` answers, built from the UNPUBLISHED working copy instead of the published revision — so a reviewer without an editor account sees exactly what the storefront would render.
     *
     * @param token The token handed out by POST /pages/editor/{page_id}/preview-grant.
     * @param langcode Language to resolve the tree for. Falls back to the page's source language, per field.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesDeliveryPreview(
        token: String,
        langcode: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/delivery/preview/{token}"
            .replace("{token}", token)

        val apiParams = mutableMapOf<String, Any?>(
            "langcode" to langcode,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}