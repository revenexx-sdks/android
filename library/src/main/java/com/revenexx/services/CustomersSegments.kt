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
 * Named groups of ORGANIZATIONS — never of people — built by hand, by rule, or both at once, plus the memberships that record which of the two a company came in by. The rule language is the one product categories use, evaluated over organization columns and settings AND over order behaviour (revenue, order count, average order value, days since the last order) read from this app's own metrics projection, because the orders app may not be joined. Rules are materialized rather than live: preview one before storing it, then recompute one segment or every segment that carries rules.
 */
class CustomersSegments(client: Client) : Service(client) {

    /**
     * One organization inside one segment, plus the record of how it got there: `source: "manual"` for a company somebody put in, `source: "rule"` for one the rule engine matched. That distinction is what lets a recompute rewrite its own rows and leave every hand-picked one alone. The membership rows themselves — the answer to "which companies are in this segment" (`segment_id`) and to "which segments is this company in" (`organization_id`). Paged with `limit`/`offset`/`order`.
     *
     * @param id Filter to rows whose `id` is exactly this value. Primary key of the membership row.
     * @param segmentId Filter to one segment — its members.
     * @param organizationId Filter to one company — the segments it belongs to. The same route answers both questions.
     * @param source Filter by how the membership came about. `manual` is the hand-picked set a recompute will never touch.
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the organization joined the segment.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersSegmentMembersList(
        id: String? = null,
        segmentId: String? = null,
        organizationId: String? = null,
        source: com.revenexx.enums.Source? = null,
        createdAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/segment_members"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "segment_id" to segmentId,
            "organization_id" to organizationId,
            "source" to source,
            "created_at" to createdAt,
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
     * One organization inside one segment, plus the record of how it got there: `source: "manual"` for a company somebody put in, `source: "rule"` for one the rule engine matched. That distinction is what lets a recompute rewrite its own rows and leave every hand-picked one alone. Adds a company to a segment BY HAND. The row is `source: "manual"`, which is what protects it: a rule recompute rewrites the rule-derived rows of that segment and never touches this one. A create cannot omit `segment_id` and `organization_id`; everything else is optional or defaulted by the database. Two rows of this tenant may not share the combination of `segment_id` + `organization_id`.
     *
     * @param organizationId The member company. Segments group companies, never people — a person is reached through their organization.
     * @param segmentId The segment.
     * @param source How this membership came about: 'manual' is hand-picked, 'rule' was materialized by a recompute. The distinction is load-bearing — a recompute only ever inserts and deletes 'rule' rows, so a hand-picked member survives every rule change. Default 'manual'.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersSegmentMembersCreate(
        organizationId: String,
        segmentId: String,
        source: com.revenexx.enums.SegmentMemberSource? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segment_members"

        val apiParams = mutableMapOf<String, Any?>(
            "organization_id" to organizationId,
            "segment_id" to segmentId,
            "source" to source,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * One organization inside one segment, plus the record of how it got there: `source: "manual"` for a company somebody put in, `source: "rule"` for one the rule engine matched. That distinction is what lets a recompute rewrite its own rows and leave every hand-picked one alone. Takes the company out of the segment. If the segment carries rules and the company still matches them, the next recompute puts it back; remove it from the rule, not from the list. Nothing else in this app points at it, so nothing else goes with it.
     *
     * @param id The segment membership to delete.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersSegmentMembersDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segment_members/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * One organization inside one segment, plus the record of how it got there: `source: "manual"` for a company somebody put in, `source: "rule"` for one the rule engine matched. That distinction is what lets a recompute rewrite its own rows and leave every hand-picked one alone. One membership row by id, with the `source` that says how it came about.
     *
     * @param id The segment membership to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersSegmentMembersGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segment_members/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
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
     * One organization inside one segment, plus the record of how it got there: `source: "manual"` for a company somebody put in, `source: "rule"` for one the rule engine matched. That distinction is what lets a recompute rewrite its own rows and leave every hand-picked one alone. A partial update. In practice there is little to change — a membership is a pair of ids — so this exists for the `source` correction rather than as the normal path. Two rows of this tenant may not share the combination of `segment_id` + `organization_id`.
     *
     * @param id The segment membership to update.
     * @param organizationId The member company. Segments group companies, never people — a person is reached through their organization.
     * @param segmentId The segment.
     * @param source How this membership came about: 'manual' is hand-picked, 'rule' was materialized by a recompute. The distinction is load-bearing — a recompute only ever inserts and deletes 'rule' rows, so a hand-picked member survives every rule change. Default 'manual'.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersSegmentMembersUpdate(
        id: String,
        organizationId: String? = null,
        segmentId: String? = null,
        source: com.revenexx.enums.SegmentMemberSource? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segment_members/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "organization_id" to organizationId,
            "segment_id" to segmentId,
            "source" to source,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A segment is a named group of ORGANIZATIONS — never of people — built by hand, by rule, or both at once. It is what a price list, a campaign or a shipping option is pointed at when the answer is "these customers, not those". Every segment this tenant keeps, with its stored rules. Any column filters and the page is `limit`/`offset`/`order`. Which companies are actually IN one is `segment_members`, because the rule half is materialized rather than evaluated on read.
     *
     * @param id Filter to rows whose `id` is exactly this value. Primary key of the segment.
     * @param code Filter by exact segment code.
     * @param position Filter to rows whose `position` is exactly this value. Sort order in the cockpit, ascending. Ties fall back to insertion order.
     * @param ruleMatch Filter to rows whose `rule_match` is exactly this value. How the conditions combine: 'all' (default) is AND, 'any' is OR. Null means the same as 'all'.
     * @param rulesComputedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the rule last finished a COMPLETE recompute. Null after a rule change, and while a chunked recompute is still running — so it doubles as "are the rule memberships trustworthy right now?".
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the segment was created.
     * @param updatedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When any column of this row last changed.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersSegmentsList(
        id: String? = null,
        code: String? = null,
        position: Long? = null,
        ruleMatch: com.revenexx.enums.RuleMatch? = null,
        rulesComputedAt: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/segments"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "code" to code,
            "position" to position,
            "rule_match" to ruleMatch,
            "rules_computed_at" to rulesComputedAt,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
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
     * A segment is a named group of ORGANIZATIONS — never of people — built by hand, by rule, or both at once. It is what a price list, a campaign or a shipping option is pointed at when the answer is "these customers, not those". Creates the group. Rules are optional: leave them out for a hand-picked list, or store a rule document and let the recompute keep the membership up to date. The `code` is what other apps point at, so pick it deliberately. `code` is the only field a create cannot omit; everything else is optional or defaulted by the database. Two rows of this tenant may not share `code`.
     *
     * @param code Stable identifier, unique per tenant — what other apps and integrations name the segment by. Free text, but lowercase with underscores is the convention every seeded vocabulary follows.
     * @param labels Localized display names keyed by language tag. Null means nobody translated it and a client falls back to showing the code.
     * @param position Sort order in the cockpit, ascending. Ties fall back to insertion order. Default 0.
     * @param ruleMatch How the conditions combine: 'all' (default) is AND, 'any' is OR. Null means the same as 'all'.
     * @param rules The selector that decides membership, stored verbatim. Null means the segment is manual-only. The same rule language product categories use, evaluated over organization columns, `setting:<key>` entries and the organization_metrics projection — so 'no order in 365 days' is expressible without joining the orders app. Null makes the segment manual-only. Changing it does not move a single membership — run the recompute.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersSegmentsCreate(
        code: String,
        labels: Any? = null,
        position: Long? = null,
        ruleMatch: com.revenexx.enums.SegmentRuleMatch? = null,
        rules: Any? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "position" to position,
            "rule_match" to ruleMatch,
            "rules" to rules,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Same sync as the single-segment recompute, applied to every segment with non-null rules. A failing segment is reported in its result entry instead of aborting the run. The run shares one budget: a segment that does not fit reports done:false (or skipped:true) and keeps rules_computed_at null, so the next call resumes it from its own data. Repeat until the top-level done is true.
     *
     * @param data Request body
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersSegmentsRulesRecomputeAll(
        data: Any,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments/rules/recompute-all"

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A segment is a named group of ORGANIZATIONS — never of people — built by hand, by rule, or both at once. It is what a price list, a campaign or a shipping option is pointed at when the answer is "these customers, not those". Removes the segment. Anything in another app that points at its `code` — a price list, a campaign — is left pointing at nothing, because no app may hold a foreign key into another (ADR-0055). Deleting one takes every `segment_members` row that points at it with it — the foreign keys decide, not this route.
     *
     * @param id The segment to delete.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersSegmentsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A segment is a named group of ORGANIZATIONS — never of people — built by hand, by rule, or both at once. It is what a price list, a campaign or a shipping option is pointed at when the answer is "these customers, not those". One segment by id, including the rule document it carries. A segment with no rules is hand-picked and completely valid.
     *
     * @param id The segment to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersSegmentsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
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
     * A segment is a named group of ORGANIZATIONS — never of people — built by hand, by rule, or both at once. It is what a price list, a campaign or a shipping option is pointed at when the answer is "these customers, not those". A partial update — send only what changes. Editing the rules does NOT re-evaluate them: that is `POST /customers/segments/{segment_id}/rules/recompute`, so a half-typed rule never silently empties a live segment. Two rows of this tenant may not share `code`.
     *
     * @param id The segment to update.
     * @param code Stable identifier, unique per tenant — what other apps and integrations name the segment by. Free text, but lowercase with underscores is the convention every seeded vocabulary follows.
     * @param labels Localized display names keyed by language tag. Null means nobody translated it and a client falls back to showing the code.
     * @param position Sort order in the cockpit, ascending. Ties fall back to insertion order. Default 0.
     * @param ruleMatch How the conditions combine: 'all' (default) is AND, 'any' is OR. Null means the same as 'all'.
     * @param rules The selector that decides membership, stored verbatim. Null means the segment is manual-only. The same rule language product categories use, evaluated over organization columns, `setting:<key>` entries and the organization_metrics projection — so 'no order in 365 days' is expressible without joining the orders app. Null makes the segment manual-only. Changing it does not move a single membership — run the recompute.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersSegmentsUpdate(
        id: String,
        code: String? = null,
        labels: Any? = null,
        position: Long? = null,
        ruleMatch: com.revenexx.enums.SegmentRuleMatch? = null,
        rules: Any? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "labels" to labels,
            "position" to position,
            "rule_match" to ruleMatch,
            "rules" to rules,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A dry run: it answers how many organizations the rule would select, with a handful of them by name, and writes nothing at all. Evaluates the rule document in the REQUEST BODY (not the stored segments.rules), so the cockpit can preview an unsaved rule. Costs a single count query for the common single-query rule; 'any' rules and rules repeating a column are combined in the app and capped at 5000 ids, in which case 'capped' is true and 'count' is a LOWER bound. Membership is never touched.
     *
     * @param segmentId The segment the preview is filed under. Its stored rules are NOT read — the rule comes from the body — but it has to exist.
     * @param conditions The conditions, combined by `rule_match`. At least one, at most 25.
     * @param ruleMatch How the conditions combine. Default 'all'.
     * @param target Only 'organizations' is supported; any other value is rejected. A segment groups COMPANIES — the people are reached through them.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersSegmentsRulesPreview(
        segmentId: String,
        conditions: List<com.revenexx.models.SegmentRuleCondition>,
        ruleMatch: com.revenexx.enums.RuleMatch? = null,
        target: com.revenexx.enums.Target? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments/{segment_id}/rules/preview"
            .replace("{segmentId}", segmentId)

        val apiParams = mutableMapOf<String, Any?>(
            "conditions" to conditions,
            "rule_match" to ruleMatch,
            "target" to target,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Evaluates segments.rules (NOT the request body), then inserts the newly matching organizations as source='rule' rows and deletes the rule rows that no longer match. Manual (source='manual') memberships are never inserted, deleted or shadowed. Bounded by a wall-clock budget below the gateway's upstream timeout: when 'done' is false, POST again with the returned 'cursor' until it is true. added/removed/processed count THIS call only. Omitting 'cursor' resumes an unfinished pass and starts a fresh one after a completed pass; an explicit null always restarts. segments.rules_computed_at is stamped only when the pass completes.
     *
     * @param segmentId The segment whose stored rules are evaluated.
     * @param cursor Continuation token from a previous response — the id of the last organization the pass touched. Omit to resume or start automatically; pass null to force a restart from the beginning.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersSegmentsRulesRecompute(
        segmentId: String,
        cursor: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/segments/{segment_id}/rules/recompute"
            .replace("{segmentId}", segmentId)

        val apiParams = mutableMapOf<String, Any?>(
            "cursor" to cursor,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}