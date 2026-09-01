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
 * Outbound multi-channel messaging (email/SMS/push): templates, event bindings, sends (the sequencer service).
 */
class Messaging(client: Client) : Service(client) {

    /**
     * Filterable by `resource_type`, `resource_id` and `subject` — the last one
     * being the human-readable name a row was recorded under (a template's key,
     * a layout's name), which is what an operator has to hand six weeks later
     * when the id means nothing to them.
     * 
     * There is no write route and no delete route: an append-only log with an
     * editor is a log that says whatever the last editor wanted.
     *
     * @param resourceType 
     * @param resourceId 
     * @param subject 
     * @param limit 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun auditIndex(
        resourceType: com.revenexx.enums.ResourceType? = null,
        resourceId: String? = null,
        subject: String? = null,
        limit: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/audit"

        val apiParams = mutableMapOf<String, Any?>(
            "resource_type" to resourceType,
            "resource_id" to resourceId,
            "subject" to subject,
            "limit" to limit,
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
     * `?event_topic=` narrows to one topic, which is the question worth asking
     * of this list: "what does this event actually do".
     *
     * @param eventTopic 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun bindingIndex(
        eventTopic: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/bindings"

        val apiParams = mutableMapOf<String, Any?>(
            "event_topic" to eventTopic,
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
     * `recipient` is a template, not an address: `{{ customer.email }}` is
     * rendered against the event payload when the event arrives, which is the
     * only way one binding can serve every customer. An event that renders it
     * empty is skipped and logged rather than sent to nobody.
     * 
     * `locale` is what the OPERATOR said this route speaks, and it outranks the
     * tenant's default. Leave it null when nobody has made that decision, so
     * that the recipient's own language is still allowed to decide.
     *
     * @param channel 
     * @param eventTopic 
     * @param recipient 
     * @param templateKey 
     * @param enabled 
     * @param fallbackOrder 
     * @param locale Nullable: a binding's locale is what the OPERATOR said this
route speaks, and it outranks the tenant's own default
(LocaleResolver). "No opinion" has to be expressible, or a route
nobody made a language decision about silently makes one.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun bindingStore(
        channel: String,
        eventTopic: String,
        recipient: String,
        templateKey: String,
        enabled: Boolean? = null,
        fallbackOrder: Long? = null,
        locale: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/bindings"

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "enabled" to enabled,
            "event_topic" to eventTopic,
            "fallback_order" to fallbackOrder,
            "locale" to locale,
            "recipient" to recipient,
            "template_key" to templateKey,
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
     * The event it answered goes back to doing nothing. Prefer `enabled: false`
     * when the intent is to pause rather than to forget.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun bindingDestroy(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/bindings/{id}"
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
     * 404 for a binding belonging to another tenant, not 403 — an id that
     * answered differently would say whether it exists.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun bindingShow(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/bindings/{id}"
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
     * Every field is optional; only what is sent is written. `enabled: false`
     * is how a binding is taken out of service without losing what it said —
     * the alternative is deleting it and typing the payload path back in
     * correctly from memory later.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param id 
     * @param channel 
     * @param enabled 
     * @param eventTopic 
     * @param fallbackOrder 
     * @param locale Nullable: a binding's locale is what the OPERATOR said this
route speaks, and it outranks the tenant's own default
(LocaleResolver). "No opinion" has to be expressible, or a route
nobody made a language decision about silently makes one.
     * @param recipient 
     * @param templateKey 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun bindingUpdatePatch(
        id: String,
        channel: String? = null,
        enabled: Boolean? = null,
        eventTopic: String? = null,
        fallbackOrder: Long? = null,
        locale: String? = null,
        recipient: String? = null,
        templateKey: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/bindings/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "enabled" to enabled,
            "event_topic" to eventTopic,
            "fallback_order" to fallbackOrder,
            "locale" to locale,
            "recipient" to recipient,
            "template_key" to templateKey,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Every field is optional; only what is sent is written. `enabled: false`
     * is how a binding is taken out of service without losing what it said —
     * the alternative is deleting it and typing the payload path back in
     * correctly from memory later.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param id 
     * @param channel 
     * @param enabled 
     * @param eventTopic 
     * @param fallbackOrder 
     * @param locale Nullable: a binding's locale is what the OPERATOR said this
route speaks, and it outranks the tenant's own default
(LocaleResolver). "No opinion" has to be expressible, or a route
nobody made a language decision about silently makes one.
     * @param recipient 
     * @param templateKey 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun bindingUpdate(
        id: String,
        channel: String? = null,
        enabled: Boolean? = null,
        eventTopic: String? = null,
        fallbackOrder: Long? = null,
        locale: String? = null,
        recipient: String? = null,
        templateKey: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/bindings/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "enabled" to enabled,
            "event_topic" to eventTopic,
            "fallback_order" to fallbackOrder,
            "locale" to locale,
            "recipient" to recipient,
            "template_key" to templateKey,
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
     * Answers per channel with: which fields the chosen provider wants and
     * which of them are SET (never their values — secrets go in and do not come
     * back), which markets hold an override, which providers this build offers,
     * whether the deployment has the channel switched on at all, the URL to
     * paste into the provider's own console so bounces and opens come back, and
     * whether callbacks are actually arriving.
     * 
     * Admin tier on the read as well as the write: the identifiers alone —
     * which Twilio account, which sender number — are more than a read-only
     * operator has reason to see, and the webhook URL served here contains the
     * tenant's callback token.
     *
     * @param market Which market's credentials this call is about. Absent means the GLOBAL bag — what every
send used before markets reached this path, and what a market with no override of its own
still uses.

Lowercase, opening with a letter, 63 characters at most (Baseline's market slug rule,
mirrored exactly). A code that does not match is refused with 422 rather than read as
"no market": on the write paths, silently falling back to global would have an operator
point every market's traffic at one market's provider while looking at a screen that said
they had not.
     * @param markets Set to `all` to get every market's credentials in one answer: each channel gains an
`overrides` object keyed by market code, holding that market's own resolved view of the
channel — its provider, which of that provider's fields are set, its callback URL, and
whether callbacks are arriving. Only markets with credentials of their OWN appear; a market
that inherits has nothing to add.

The channel's top-level entry is the GLOBAL one whenever this is set, and `?market=` is
ignored: `all` is not a market to resolve against, and honouring both would leave the
base entry meaning something different depending on a header.

The override entries carry no `providers` catalogue, `enabled` flag or `markets` list.
Those are properties of the channel, identical in every market, and repeating
twenty-six providers' field specifications per market would be most of the response.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun channelCredentialIndex(
        market: String? = null,
        markets: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/channel-credentials"

        val apiParams = mutableMapOf<String, Any?>(
            "market" to market,
            "markets" to markets,
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
     * With `?market=`, only that market's override goes and the global
     * credentials stand — the market then sends over the global provider again,
     * which is what it did before anybody configured it. Without a market the
     * channel goes entirely, overrides and all: a caller asking for a channel
     * to hold no credentials means all of them.
     * 
     * 204 whether or not anything was there. The caller wants this channel to
     * hold no credentials, and it does.
     *
     * @param channel 
     * @param market Which market's credentials this call is about. Absent means the GLOBAL bag — what every
send used before markets reached this path, and what a market with no override of its own
still uses.

Lowercase, opening with a letter, 63 characters at most (Baseline's market slug rule,
mirrored exactly). A code that does not match is refused with 422 rather than read as
"no market": on the write paths, silently falling back to global would have an operator
point every market's traffic at one market's provider while looking at a screen that said
they had not.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun channelCredentialDestroy(
        channel: String,
        market: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/channel-credentials/{channel}"
            .replace("{channel}", channel)

        val apiParams = mutableMapOf<String, Any?>(
            "market" to market,
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
     * A PATCH in spirit whichever verb is used: only the fields present in the
     * body are written, and the answer says which of them actually CHANGED, so
     * a form that resent everything it had on screen does not report a change
     * that did not happen.
     * 
     * Three refusals, all 422 and all deliberate rather than ignored. A field
     * the channel's provider does not have (`unknown_credential_field`) — a
     * typo sitting in the bag looking like configuration fails later with a
     * message about a MISSING field the operator can see they filled in. A
     * field the platform issues (`managed_credential`) — ignoring it would have
     * the caller believe they set something. A channel with nothing to
     * configure (`channel_not_configurable`), which is push: its VAPID keypair
     * is generated at provisioning, and pasting a new one would orphan every
     * browser registration the tenant has collected.
     * 
     * Switching provider is `driver`, and the fields in the same request are
     * validated against the provider being switched TO — validating Postmark's
     * key against Mailgun's field list is how a switch loses everything the
     * operator just typed.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param channel 
     * @param market Which market's credentials this call is about. Absent means the GLOBAL bag — what every
send used before markets reached this path, and what a market with no override of its own
still uses.

Lowercase, opening with a letter, 63 characters at most (Baseline's market slug rule,
mirrored exactly). A code that does not match is refused with 422 rather than read as
"no market": on the write paths, silently falling back to global would have an operator
point every market's traffic at one market's provider while looking at a screen that said
they had not.
     * @param driver 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun channelCredentialUpdatePatch(
        channel: String,
        market: String? = null,
        driver: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/channel-credentials/{channel}"
            .replace("{channel}", channel)

        val apiParams = mutableMapOf<String, Any?>(
            "market" to market,
            "driver" to driver,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A PATCH in spirit whichever verb is used: only the fields present in the
     * body are written, and the answer says which of them actually CHANGED, so
     * a form that resent everything it had on screen does not report a change
     * that did not happen.
     * 
     * Three refusals, all 422 and all deliberate rather than ignored. A field
     * the channel's provider does not have (`unknown_credential_field`) — a
     * typo sitting in the bag looking like configuration fails later with a
     * message about a MISSING field the operator can see they filled in. A
     * field the platform issues (`managed_credential`) — ignoring it would have
     * the caller believe they set something. A channel with nothing to
     * configure (`channel_not_configurable`), which is push: its VAPID keypair
     * is generated at provisioning, and pasting a new one would orphan every
     * browser registration the tenant has collected.
     * 
     * Switching provider is `driver`, and the fields in the same request are
     * validated against the provider being switched TO — validating Postmark's
     * key against Mailgun's field list is how a switch loses everything the
     * operator just typed.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param channel 
     * @param market Which market's credentials this call is about. Absent means the GLOBAL bag — what every
send used before markets reached this path, and what a market with no override of its own
still uses.

Lowercase, opening with a letter, 63 characters at most (Baseline's market slug rule,
mirrored exactly). A code that does not match is refused with 422 rather than read as
"no market": on the write paths, silently falling back to global would have an operator
point every market's traffic at one market's provider while looking at a screen that said
they had not.
     * @param driver 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun channelCredentialUpdate(
        channel: String,
        market: String? = null,
        driver: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/channel-credentials/{channel}"
            .replace("{channel}", channel)

        val apiParams = mutableMapOf<String, Any?>(
            "market" to market,
            "driver" to driver,
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
     * The one thing that turns this screen from a form into a tool. Credentials
     * that only fail at send time cost a customer their first order
     * confirmation, and by then nobody connects the failure to the afternoon
     * somebody pasted a key with a trailing space.
     * 
     * **Always 200.** The answer is `{ok, message}` in the body, including when
     * the credentials are wrong: the REQUEST was fine, the credentials are not,
     * and a 4xx here would have the cockpit's own error handling swallow the
     * one sentence worth reading. A channel that asks for no credentials at all
     * (push, in-app) answers `ok: true` — "nothing to verify" is a finished
     * check, not a failed one, and reporting it as an error painted a channel
     * that has worked since provisioning in the same red as a wrong token.
     *
     * @param channel 
     * @param market Which market's credentials this call is about. Absent means the GLOBAL bag — what every
send used before markets reached this path, and what a market with no override of its own
still uses.

Lowercase, opening with a letter, 63 characters at most (Baseline's market slug rule,
mirrored exactly). A code that does not match is refused with 422 rather than read as
"no market": on the write paths, silently falling back to global would have an operator
point every market's traffic at one market's provider while looking at a screen that said
they had not.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun channelCredentialVerify(
        channel: String,
        market: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/channel-credentials/{channel}/verify"
            .replace("{channel}", channel)

        val apiParams = mutableMapOf<String, Any?>(
            "market" to market,
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * Each entry says whether the channel is switched on and which provider
     * carries it by default. A channel that is off will refuse a send, so a UI
     * that offers a channel picker should build it from this rather than from a
     * list of its own — a channel added to the service then appears without a
     * release of the client.
     *
     * @return [com.revenexx.models.Error]
     */
    suspend fun channelIndex(
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/channels"

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
     * A tenant that was never provisioned has no row and still gets an answer:
     * an empty shape rather than a 404, so the Cockpit's panels open on
     * editable blanks instead of an error.
     * 
     * `meta.push_public_key` is the VAPID public key, and only the public one.
     * A storefront cannot call `PushManager.subscribe()` without it, so it has
     * to leave the service; the private half and every provider secret stay
     * hidden on the model, where they are protected on every route rather than
     * on this one.
     *
     * @return [com.revenexx.models.Error]
     */
    suspend fun configShow(
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/config"

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
     * Reaches every message this tenant sends, including templates saved months
     * ago — content placeholders resolve at send time, not at save time — which
     * is why writing is admin tier while reading is not.
     * 
     * Two refusals worth knowing about. `defaults.brand` is 422, not ignored:
     * the letterhead moved to /v1/layouts when a tenant gained more than one of
     * them, and a letterhead edit that appears to save and changes nothing is
     * the worst of the three possible behaviours. A half-written `quiet_hours`
     * is 422 as well — a tenant that typed a start and forgot the end has an
     * opinion about when not to message people, and silently sending through
     * the night is the one answer that is definitely wrong.
     * 
     * Provider credentials cannot be written here. That path is
     * /v1/channel-credentials, so the one route that handles secrets stays the
     * one that was built for it.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param defaultLocale The house language — step 4 of the send path's resolution order,
reached only when neither the caller, the event payload nor the
binding said anything. A column of its own and not a key in
`defaults` below, because everything in that bag is merged into
the render model: a `locale` key there would start filling
`{{ locale }}` inside template bodies, which is a routing
decision leaking into content.
     * @param defaults The saved modules live in here. The shape is the Cockpit's
contract and is not pinned down further: adding a block type
would otherwise be a service deploy. The one key that IS pinned
down is `brand`, because it moved out — and it is refused with a
closure rather than a `defaults.brand` rule, since a nested rule
makes the validator drop the parent and quietly discard every
other key in the bag along with it.
     * @param product 
     * @param quietHours 
     * @param supportEmail 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun configUpdatePatch(
        defaultLocale: String? = null,
        defaults: List<String>? = null,
        product: String? = null,
        quietHours: List<String>? = null,
        supportEmail: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/config"

        val apiParams = mutableMapOf<String, Any?>(
            "default_locale" to defaultLocale,
            "defaults" to defaults,
            "product" to product,
            "quiet_hours" to quietHours,
            "support_email" to supportEmail,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Reaches every message this tenant sends, including templates saved months
     * ago — content placeholders resolve at send time, not at save time — which
     * is why writing is admin tier while reading is not.
     * 
     * Two refusals worth knowing about. `defaults.brand` is 422, not ignored:
     * the letterhead moved to /v1/layouts when a tenant gained more than one of
     * them, and a letterhead edit that appears to save and changes nothing is
     * the worst of the three possible behaviours. A half-written `quiet_hours`
     * is 422 as well — a tenant that typed a start and forgot the end has an
     * opinion about when not to message people, and silently sending through
     * the night is the one answer that is definitely wrong.
     * 
     * Provider credentials cannot be written here. That path is
     * /v1/channel-credentials, so the one route that handles secrets stays the
     * one that was built for it.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param defaultLocale The house language — step 4 of the send path's resolution order,
reached only when neither the caller, the event payload nor the
binding said anything. A column of its own and not a key in
`defaults` below, because everything in that bag is merged into
the render model: a `locale` key there would start filling
`{{ locale }}` inside template bodies, which is a routing
decision leaking into content.
     * @param defaults The saved modules live in here. The shape is the Cockpit's
contract and is not pinned down further: adding a block type
would otherwise be a service deploy. The one key that IS pinned
down is `brand`, because it moved out — and it is refused with a
closure rather than a `defaults.brand` rule, since a nested rule
makes the validator drop the parent and quietly discard every
other key in the bag along with it.
     * @param product 
     * @param quietHours 
     * @param supportEmail 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun configUpdate(
        defaultLocale: String? = null,
        defaults: List<String>? = null,
        product: String? = null,
        quietHours: List<String>? = null,
        supportEmail: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/config"

        val apiParams = mutableMapOf<String, Any?>(
            "default_locale" to defaultLocale,
            "defaults" to defaults,
            "product" to product,
            "quiet_hours" to quietHours,
            "support_email" to supportEmail,
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
     * The order is the list's purpose: it is a picker, and the entry most
     * templates are actually on belongs at the top of it.
     * 
     * Market-scoped as a browsing filter — see the parameters. `GET /layouts/{id}`
     * deliberately is not: somebody holding an id may read it.
     *
     * @param markets Set to `all` for the unscoped read: every row whatever its markets, ignoring the `X-Revenexx-Market` header. The deliberate admin case, spelled in the query string so it is asked for rather than fallen into. No other value has any effect.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun layoutIndex(
        markets: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/layouts"

        val apiParams = mutableMapOf<String, Any?>(
            "markets" to markets,
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
     * A tenant's FIRST layout becomes the default whatever the request says: a
     * tenant with no default cannot compile a template that does not name one.
     * 
     * The default may hold neither a validity window nor `enabled: false`, and
     * asking for both in one request is refused with 422
     * `layout_default_always_in_force`. There is no fallback behind the default
     * — every template that names no layout is framed by it — so a window set
     * today would take a tenant's whole letterhead away on a morning months
     * from now, with nobody left who remembers typing the date.
     *
     * @return [com.revenexx.models.Error]
     */
    suspend fun layoutStore(
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/layouts"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * Answers 200 with a body rather than the 204 the other resources use: the
     * count of reassigned templates is the part an operator needs, and a
     * deletion that silently moved eleven templates onto another letterhead is
     * one they would only discover from the next mail that went out.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun layoutDestroy(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/layouts/{id}"
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
     * Not market-filtered, deliberately: market scoping is a browsing concern,
     * and somebody holding an id may read the row. A template pinned to a
     * layout keeps mailing on it whatever market the reader is looking at.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun layoutShow(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/layouts/{id}"
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
     * The change reaches every template on this layout, including ones saved
     * months ago and never opened since — which is exactly the change nobody
     * remembers making when the mails start looking wrong. It is audited for
     * that reason, and only when something actually changed: an audit line on
     * every save teaches its readers to ignore the log.
     * 
     * Two 422s. Clearing `is_default` on the current default is
     * `layout_default_required` — promoting another layout is the operation
     * that exists for this, and it clears this one as a side effect, which is
     * the only way the count stays at exactly one. Giving the default a
     * validity window or switching it off is `layout_default_always_in_force`,
     * and the check is made of the OUTCOME, so promoting a layout and dating it
     * in the same request is caught.
     * 
     * The structural half of a layout — colours, width, font — is baked into
     * each template's compiled body, so templates already on it keep the old
     * one until they are recompiled.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun layoutUpdate(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/layouts/{id}"
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
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * What the Cockpit's "start from a template" gallery is built from. These
     * are not the tenant's rows and cannot be edited here: provisioning clones
     * them into `/v1/templates`, and it is the clone that a tenant owns.
     *
     * @param channel 
     * @param locale 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun libraryIndex(
        channel: String? = null,
        locale: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/library"

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "locale" to locale,
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
     * `?channel=` and `?status=` narrow it; `?limit=` is clamped to 200 and
     * defaults to 50. `?channel=inapp` is the tenant's in-app inbox — the
     * Message row IS the inbox item, so there is no second store for it.
     * 
     * Rows are subject to the deployment's retention window and to erasure
     * requests, so this is not an archive.
     *
     * @param channel 
     * @param status 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun messageIndex(
        channel: String? = null,
        status: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/messages"

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "status" to status,
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
     * Carries the render model it was sent with, so "why did this mail say
     *      * that" is answerable after the fact. That is also why the row is personal
     * data and why it can be erased — see POST /v1/privacy/erasures.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun messageShow(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/messages/{id}"
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
     * Answers with the resolved subject, HTML and text exactly as a real send
     * would produce them, so an editor can show a faithful preview without a
     * message row, a provider call or a suppression check.
     * 
     * Takes no `market`, deliberately: rendering picks no provider, so there is
     * nothing here for a market to change. Nor `send_at`, `draft` or
     * `attachments` — all of them are properties of a dispatch, not of a render.
     *
     * @param channel 
     * @param template 
     * @param data The render model: a free map of variable name to value, resolved against the template's
placeholders. Values may be strings, numbers, booleans, or nested objects and arrays —
`{{ order.number }}` reads a nested one.

Not the only source. A tenant's `defaults`, its layout, and the template's own
`variable_defaults` are merged underneath, so a placeholder an event did not carry can
still resolve. Anything named here wins over all of them.
     * @param locale 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun sendPreview(
        channel: String,
        template: String,
        data: Any? = null,
        locale: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/preview"

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "data" to data,
            "locale" to locale,
            "template" to template,
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
     * Per (channel, address), because an address is channel-shaped and the rows
     * it has to line up with are keyed that way. Matching is done on the
     * normalised form on both sides, so a request for `ada@acme.test` finds a
     * log written for `Ada@Acme.test` — an erasure that misses on
     * capitalisation is an erasure that did not happen and reports success.
     * 
     * Message rows and unsubscribe tokens are DELETED. Suppressions are KEPT
     * with the clear-text address nulled: matching runs on a keyed hash, so the
     * row can still block and can no longer identify. Deleting it instead is
     * the obvious reading of "erase everything about them", and it is the
     * reading that mails a dead address again next week — or mails somebody who
     * complained, which is how a sending domain gets blocked.
     * 
     * Answers with the counts, `suppressions_kept` among them, so the design is
     * stated in the response rather than only in this paragraph.
     *
     * @param address 
     * @param channel Per (channel, address), not per address: an address is
channel-shaped, and the suppression and token rows it has to line
up with are keyed that way.
     * @return [com.revenexx.models.Error]
     */
    suspend fun erasureStore(
        address: String,
        channel: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/privacy/erasures"

        val apiParams = mutableMapOf<String, Any?>(
            "address" to address,
            "channel" to channel,
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
     * By endpoint and not by id, because the browser knows its endpoint and has
     * never seen our id — this is called from a service worker reacting to
     * `pushsubscriptionchange`, or from a "turn off notifications" button.
     *
     * @param endpoint 
     * @return [com.revenexx.models.Error]
     */
    suspend fun pushSubscriptionDestroy(
        endpoint: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/push/subscriptions"

        val apiParams = mutableMapOf<String, Any?>(
            "endpoint" to endpoint,
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
     * `subscriber_id` is required: this is not a list of everybody, and there
     * is no route that is. The caller is a storefront acting for one visitor
     * and has no business enumerating the rest.
     * 
     * The client key material is never returned — see the `$hidden` list on the
     * model. A registration that can be read back is a registration somebody
     * else can push with.
     *
     * @param subscriberId 
     * @return [com.revenexx.models.Error]
     */
    suspend fun pushSubscriptionIndex(
        subscriberId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/push/subscriptions"

        val apiParams = mutableMapOf<String, Any?>(
            "subscriber_id" to subscriberId,
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
     * Send what `PushManager.subscribe()` handed back — the endpoint and the
     * two keys — plus the id you know that person by. The VAPID public key the
     * browser needs to produce it comes from `GET /v1/config`
     * (`meta.push_public_key`).
     * 
     * **Idempotent by endpoint**, and the two statuses say which happened: 201
     * for a browser seen for the first time, 200 for one already registered. A
     * browser calls `subscribe()` on every page load and hands back the same
     * endpoint each time; treating that as a new device would give one laptop a
     * thousand rows and push to it a thousand times.
     *
     * @param endpoint 
     * @param keys 
     * @param subscriberId 
     * @param userAgent 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pushSubscriptionStore(
        endpoint: String,
        keys: Any,
        subscriberId: String,
        userAgent: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/push/subscriptions"

        val apiParams = mutableMapOf<String, Any?>(
            "endpoint" to endpoint,
            "keys" to keys,
            "subscriber_id" to subscriberId,
            "user_agent" to userAgent,
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
     * Renders a tenant template and dispatches it — now, at `send_at`, or at
     * the end of the tenant's quiet hours.
     * 
     * The first line is deliberately a title, not a sentence about the
     * mechanism: Scramble takes it as the operation's `summary`, and a summary
     * is what an API explorer prints in its route list. The paragraph that used
     * to be here ran to 119 characters across two lines, which the gateway's
     * fragment tests reject for exactly that reason.
     * 
     * Retry-safe when the caller sends an `Idempotency-Key` header. The two
     * answers are deliberately different:
     * 
     *   201 — a message was created by THIS call
     *   200 — this key was already used; here is the message it produced
     * 
     * A caller has to be able to tell those apart. "Your mail went out" and
     * "your mail had already gone out" are the same outcome and different
     * facts, and a client reconciling its own records needs the second one.
     * Same key with a different body is a 422 — see IdempotencyConflict.
     * 
     * A recipient on the tenant's suppression list is not sent to, and that is
     * reported as a refusal rather than as a silent success.
     *
     * @param channel 
     * @param template 
     * @param to 
     * @param attachments Files travelling with the message. Base64 content, never a URL:
fetching an address that arrives in a request body would make
this service a request-forwarder inside the platform network —
see App\Support\Attachment.
     * @param data The render model: a free map of variable name to value, resolved against the template's
placeholders. Values may be strings, numbers, booleans, or nested objects and arrays —
`{{ order.number }}` reads a nested one.

Not the only source. A tenant's `defaults`, its layout, and the template's own
`variable_defaults` are merged underneath, so a placeholder an event did not carry can
still resolve. Anything named here wins over all of them.
     * @param draft A TEST SEND. Renders the draft instead of the published snapshot,
which is the only way an author can check a correction in a real
mail client before it goes live to everybody. Deliberately a flag on this route and not a route of its own:
everything else about it — suppression, quiet hours, the
language chain, idempotency — has to behave exactly as a real
send, and a second endpoint is a second set of those rules that
drifts. The one difference is which fassung is rendered.
     * @param locale The language the CALLER states — step 1 of the resolution order,
ahead of anything in the payload. Absent is normal and is not
"English": it means the recipient's own language decides.
     * @param market Which market this send belongs to. Absent means the GLOBAL
market, which is what every send was before markets reached this
path — so a caller that never heard of them keeps working and
gets the credentials it always had. The caller states it; nothing here derives it. A country code on
a phone number is a fact and a domain on an address is a guess,
and a guess that decides which carrier carries a message would
look exactly like a decision somebody made.

Not on `preview`: rendering picks no provider, so there is
nothing there for a market to change.
     * @param sendAt Send later. A time in the past is accepted and means now — a
client retrying a request it built ten minutes ago is asking for
the same send, and refusing it turns a late retry into a lost
message.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun sendSend(
        channel: String,
        template: String,
        to: String,
        attachments: List<Any>? = null,
        data: Any? = null,
        draft: Boolean? = null,
        locale: String? = null,
        market: String? = null,
        sendAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/send"

        val apiParams = mutableMapOf<String, Any?>(
            "attachments" to attachments,
            "channel" to channel,
            "data" to data,
            "draft" to draft,
            "locale" to locale,
            "market" to market,
            "send_at" to sendAt,
            "template" to template,
            "to" to to,
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
     * Either `days` (a window ending now, default 30) or an explicit `from`/`to`
     * span. Both ends of the span or neither: `from` alone would be an open
     * range and the service would have to guess which end was meant.
     * 
     * Three numbers are deliberately not the naive ones, and the `window` block
     * says so rather than leaving a chart to imply otherwise. The window is
     * CLAMPED to the tenant's retention, and `clamped_by_retention` says when
     * that happened — 90 days on a 30-day retention is 30 days of data wearing
     * a 90-day label, and the trend line it draws invents a collapse that never
     * happened. Opens are counted only over channels that can report them; SMS
     * and push have no such thing, so dividing opens by all messages would
     * quietly halve every open rate the moment a tenant adds a second channel.
     * The delivery rate is sent ÷ (sent + failed): suppressed is the service
     * doing what it was told, and counting it as a failure would punish a
     * tenant for having a working unsubscribe list.
     * 
     * `previous` is the same window again immediately before this one, which is
     * what turns a figure into a direction. **It is null** whenever the
     * preceding window is not entirely inside retention: the query would answer
     * zero rather than fail, and zero against 1,337 renders as a triumphant
     * +100 % beside every tile on the screen. Show no trend rather than a
     * flattering one.
     * 
     * Nothing here names a recipient. That is the delivery log, which is a
     * different endpoint with a different question.
     *
     * @param days Clamped and possibly shortened by retention inside the service,
which reports what it actually used.
     * @param from An explicit span, for a window that does not end today. Both
ends or neither: `from` alone would be an open range, and the
service would have to guess which end was meant.
`nullable` rather than `sometimes`, so `required_with` still
runs when the OTHER end is missing. With `sometimes` an absent
field is skipped entirely, and `?from=` alone sailed through to
become a window nobody asked for.
     * @param to The other end of the same span, inclusive: the whole of this day
is inside the window whatever time its rows carry. A span running
past today ends today — there is no data ahead of now, and a
window with a future edge draws the series short against an axis
claiming a month nobody has lived through.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun statsIndex(
        days: Long? = null,
        from: String? = null,
        to: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/stats"

        val apiParams = mutableMapOf<String, Any?>(
            "days" to days,
            "from" to from,
            "to" to to,
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
     * Filterable by `channel`, `scope`, `reason` and `address`. The address
     * filter is looked up by FINGERPRINT rather than against the address
     * column, which is what makes "why did this person stop getting our mail"
     * answerable for somebody who has since been erased: the row has no
     * address left to match on, and the question is still the same question.
     *
     * @param channel 
     * @param scope 
     * @param reason 
     * @param address 
     * @param limit 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun suppressionIndex(
        channel: String? = null,
        scope: com.revenexx.enums.Scope? = null,
        reason: com.revenexx.enums.Reason? = null,
        address: String? = null,
        limit: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/suppressions"

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "scope" to scope,
            "reason" to reason,
            "address" to address,
            "limit" to limit,
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
     * 201 for a row this call created, 200 for an address that was already on
     * the list — so a client can tell whether it changed anything.
     * 
     * The `scope` follows from the `reason` for every reason but `manual`, and
     * asking for a different one is 422 `suppression_scope_fixed` rather than
     * being quietly corrected: a caller who asked for `marketing` on a hard
     * bounce has the model wrong, and a silent upgrade to `all` would leave
     * them believing transactional mail still flows to an address that does not
     * exist.
     *
     * @param address 
     * @param channel 
     * @param reason 
     * @param expiresAt 
     * @param note 
     * @param scope 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun suppressionStore(
        address: String,
        channel: String,
        reason: com.revenexx.enums.Reason,
        expiresAt: String? = null,
        note: String? = null,
        scope: com.revenexx.enums.Scope? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/suppressions"

        val apiParams = mutableMapOf<String, Any?>(
            "address" to address,
            "channel" to channel,
            "expires_at" to expiresAt,
            "note" to note,
            "reason" to reason,
            "scope" to scope,
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
     * Audited, unlike most deletes in this service. Removing a row here is the
     * one operation that makes the service mail an address something decided
     * not to mail — if a complaint turns into a spam report later, "who took
     *      * this off the list, and when" is the whole investigation.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun suppressionDestroy(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/suppressions/{id}"
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
     * `address` may be null: that is a person who has been erased
     * (POST /v1/privacy/erasures). The row survives as a hash, which is the
     * point — the clear text is gone and the address is still blocked.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun suppressionShow(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/suppressions/{id}"
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
     * `?channel=` narrows to one channel. Market-scoped as a BROWSING filter:
     * with `X-Revenexx-Market` the list is the global rows plus that market's,
     * without it the global rows only, and `?markets=all` is the unscoped read.
     * Never a boundary — the tenant is fixed by the credential and by row-level
     * security, and no value of either parameter reaches another tenant's rows.
     *
     * @param channel 
     * @param markets Set to `all` for the unscoped read: every row whatever its markets, ignoring the `X-Revenexx-Market` header. The deliberate admin case, spelled in the query string so it is asked for rather than fallen into. No other value has any effect.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun templateIndex(
        channel: String? = null,
        markets: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates"

        val apiParams = mutableMapOf<String, Any?>(
            "channel" to channel,
            "markets" to markets,
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
     * Send a `design` document and the service compiles it against the
     * template's layout — or send `body_html` and `body_text` yourself and skip
     * compilation entirely.
     * 
     * A design that the compiler refuses is 422 and NOTHING is written, with
     * `error.details` naming the offending block. That order is deliberate: a
     * save whose compile failed must leave the row alone, because storing the
     * design while keeping a stale body would hand the next send a mail that no
     * longer matches the document it claims to be built from, and nothing would
     * ever surface it. A sidecar that is down is 503 `mjml_unavailable`, which
     * is worth retrying; a rejected design is not.
     * 
     * The row this creates is a DRAFT and sends nothing until it is published.
     *
     * @param channel 
     * @param key 
     * @param bodyHtml 
     * @param bodyText 
     * @param contentSid The Meta-approved template this one is sent as. Outside the
24-hour service window it is the only thing WhatsApp carries.
     * @param design The design document (v2). Validated as "an array" and no further:
the compiler is the authority on the block schema and answers with
the offending block, which is a better error than anything a
validation rule list could restate here.
     * @param enabled 
     * @param layoutId Which letterhead this template is mailed on. Null (or absent) is
not "no layout" — it means the tenant's default, resolved on
every compile and every send, so the template keeps following
that default when it changes.
     * @param locale 
     * @param markets Which markets this template is browsed in. `[]` — the default —
is global, so this is never nullable: null would be a second
empty next to the one that already carries the meaning.
     * @param messageClass What messages from this template ARE. Defaulted in the column
rather than here, so a client that has never heard of the field
keeps sending transactional mail — which is what every template
written before this field existed was.
     * @param subject 
     * @param testMode When this template is in force — see App\Models\Template. `after_or_equal` and not `after`: a window of a single instant is
a legitimate thing to write while somebody is lining two
templates up back to back, and rejecting it would only make them
add a second nobody can see. A window that runs BACKWARDS is
refused, because it is a template that can never send and looks
from the list exactly like one that can.
     * @param title What the template is CALLED, as opposed to `key`, which is what
it is addressed by. Without it a list has to derive a name from
the key, and `order-confirmation` becomes "Order Confirmation" —
passable English by accident and wrong in every other language.
     * @param validFrom 
     * @param validUntil 
     * @param variableDefaults Fallbacks for the placeholders an event did not fill — a map of
variable name → string. Nullable, unlike `markets`: an empty map
and no map are the same thing (nothing to fall back to), so there
is no second state for a null to confuse anybody with.
     * @param variables 
     * @param whatsappCategory What a WhatsApp template is to Meta, which is what every message
from it COSTS: marketing runs about five times utility, and in
Germany that is roughly $0.12 against $0.025 a message. Refused
rather than coerced when it is not one of Meta's four — a
misspelled category that quietly became the default would be
wrong on an invoice nobody reads until the quarter closes.
Nullable: it is not a fact about an e-mail template, and what an
unset one means is decided on read (Template::whatsappCategory).
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun templateStore(
        channel: String,
        key: String,
        bodyHtml: String? = null,
        bodyText: String? = null,
        contentSid: String? = null,
        design: List<String>? = null,
        enabled: Boolean? = null,
        layoutId: String? = null,
        locale: String? = null,
        markets: List<String>? = null,
        messageClass: com.revenexx.enums.MessageClass? = null,
        subject: String? = null,
        testMode: Boolean? = null,
        title: String? = null,
        validFrom: String? = null,
        validUntil: String? = null,
        variableDefaults: List<String>? = null,
        variables: List<String>? = null,
        whatsappCategory: com.revenexx.enums.WhatsappCategory? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates"

        val apiParams = mutableMapOf<String, Any?>(
            "body_html" to bodyHtml,
            "body_text" to bodyText,
            "channel" to channel,
            "content_sid" to contentSid,
            "design" to design,
            "enabled" to enabled,
            "key" to key,
            "layout_id" to layoutId,
            "locale" to locale,
            "markets" to markets,
            "message_class" to messageClass,
            "subject" to subject,
            "test_mode" to testMode,
            "title" to title,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
            "variable_defaults" to variableDefaults,
            "variables" to variables,
            "whatsapp_category" to whatsappCategory,
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
     * Any binding still naming this template's key will find nothing when its
     * event next arrives. Audited under the KEY as well as the id: after the
     * delete the id resolves to nothing, and "deleted tmpl_01J…" is not
     * something an operator can act on six weeks later.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun templateDestroy(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{id}"
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
     * What customers are receiving is the published snapshot; see
     * `GET /v1/templates/{id}/versions`, whose `meta.has_unpublished_changes`
     * says whether the two differ.
     * 
     * Not market-filtered, deliberately: market scoping is a browsing concern
     * and somebody holding an id may read the row.
     *
     * @param id 
     * @return [com.revenexx.models.Error]
     */
    suspend fun templateShow(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{id}"
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
     * Only the fields sent are written, and the change is audited only when
     * something actually changed — a PATCH that resent the same values records
     * nothing, because an audit line on every save teaches its readers to
     * ignore the log.
     * 
     * Moving a template to another layout recompiles it against the NEW one,
     * even when nothing else changed: colours, width and font come from the
     * layout and are already inlined, so a template that merely changed hands
     * would otherwise keep showing the old letterhead until somebody happened
     * to press save on it again.
     * 
     * Changes nothing customers receive until the template is published.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param id 
     * @param bodyHtml 
     * @param bodyText 
     * @param contentSid 
     * @param design 
     * @param enabled 
     * @param layoutId 
     * @param markets 
     * @param messageClass 
     * @param subject 
     * @param testMode When this template is in force — see App\Models\Template. `after_or_equal` and not `after`: a window of a single instant is
a legitimate thing to write while somebody is lining two
templates up back to back, and rejecting it would only make them
add a second nobody can see. A window that runs BACKWARDS is
refused, because it is a template that can never send and looks
from the list exactly like one that can.
     * @param title Reclassifying is allowed and changes nothing that already went
out: `messages.message_class` was copied onto each row at
dispatch, so the log keeps saying what each message was.
     * @param validFrom 
     * @param validUntil 
     * @param variableDefaults 
     * @param variables 
     * @param whatsappCategory Recategorising is allowed and takes effect on the next send: Meta
move templates between categories on their own schedule, and a
row that could not follow them would go on quoting a price that
stopped being true.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun templateUpdatePatch(
        id: String,
        bodyHtml: String? = null,
        bodyText: String? = null,
        contentSid: String? = null,
        design: List<String>? = null,
        enabled: Boolean? = null,
        layoutId: String? = null,
        markets: List<String>? = null,
        messageClass: com.revenexx.enums.MessageClass? = null,
        subject: String? = null,
        testMode: Boolean? = null,
        title: String? = null,
        validFrom: String? = null,
        validUntil: String? = null,
        variableDefaults: List<String>? = null,
        variables: List<String>? = null,
        whatsappCategory: com.revenexx.enums.WhatsappCategory? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "body_html" to bodyHtml,
            "body_text" to bodyText,
            "content_sid" to contentSid,
            "design" to design,
            "enabled" to enabled,
            "layout_id" to layoutId,
            "markets" to markets,
            "message_class" to messageClass,
            "subject" to subject,
            "test_mode" to testMode,
            "title" to title,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
            "variable_defaults" to variableDefaults,
            "variables" to variables,
            "whatsapp_category" to whatsappCategory,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Only the fields sent are written, and the change is audited only when
     * something actually changed — a PATCH that resent the same values records
     * nothing, because an audit line on every save teaches its readers to
     * ignore the log.
     * 
     * Moving a template to another layout recompiles it against the NEW one,
     * even when nothing else changed: colours, width and font come from the
     * layout and are already inlined, so a template that merely changed hands
     * would otherwise keep showing the old letterhead until somebody happened
     * to press save on it again.
     * 
     * Changes nothing customers receive until the template is published.
     * 
     * This path answers on `PUT` and `PATCH`, both routed to the same action.
     *
     * @param id 
     * @param bodyHtml 
     * @param bodyText 
     * @param contentSid 
     * @param design 
     * @param enabled 
     * @param layoutId 
     * @param markets 
     * @param messageClass 
     * @param subject 
     * @param testMode When this template is in force — see App\Models\Template. `after_or_equal` and not `after`: a window of a single instant is
a legitimate thing to write while somebody is lining two
templates up back to back, and rejecting it would only make them
add a second nobody can see. A window that runs BACKWARDS is
refused, because it is a template that can never send and looks
from the list exactly like one that can.
     * @param title Reclassifying is allowed and changes nothing that already went
out: `messages.message_class` was copied onto each row at
dispatch, so the log keeps saying what each message was.
     * @param validFrom 
     * @param validUntil 
     * @param variableDefaults 
     * @param variables 
     * @param whatsappCategory Recategorising is allowed and takes effect on the next send: Meta
move templates between categories on their own schedule, and a
row that could not follow them would go on quoting a price that
stopped being true.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun templateUpdate(
        id: String,
        bodyHtml: String? = null,
        bodyText: String? = null,
        contentSid: String? = null,
        design: List<String>? = null,
        enabled: Boolean? = null,
        layoutId: String? = null,
        markets: List<String>? = null,
        messageClass: com.revenexx.enums.MessageClass? = null,
        subject: String? = null,
        testMode: Boolean? = null,
        title: String? = null,
        validFrom: String? = null,
        validUntil: String? = null,
        variableDefaults: List<String>? = null,
        variables: List<String>? = null,
        whatsappCategory: com.revenexx.enums.WhatsappCategory? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "body_html" to bodyHtml,
            "body_text" to bodyText,
            "content_sid" to contentSid,
            "design" to design,
            "enabled" to enabled,
            "layout_id" to layoutId,
            "markets" to markets,
            "message_class" to messageClass,
            "subject" to subject,
            "test_mode" to testMode,
            "title" to title,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
            "variable_defaults" to variableDefaults,
            "variables" to variables,
            "whatsapp_category" to whatsappCategory,
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
     * Answers 200 with the version already live when there was nothing to
     * publish, and 201 when a new one was written — so a client can tell
     * whether its press did anything without diffing the payload.
     *
     * @param templateId 
     * @param note 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun templateVersionStore(
        templateId: String,
        note: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{templateId}/publish"
            .replace("{templateId}", templateId)

        val apiParams = mutableMapOf<String, Any?>(
            "note" to note,
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
     * Summaries only: version, subject, message class, layout, who published it
     * and when, and their note. The BODIES are deliberately absent — a compiled
     * `body_html` runs to tens of kilobytes, and a template with forty versions
     * would make this a several-megabyte download that nobody scrolls to the
     * end of. `GET /v1/templates/{id}/versions/{version}` serves the full
     * snapshot for the one somebody actually opened.
     * 
     * `meta.published_version_id` says which of them is live — a property of
     * the template, said once, rather than a flag repeated on every row that
     * two rows could then claim. `meta.has_unpublished_changes` says whether
     * the draft has moved on since.
     *
     * @param templateId 
     * @return [com.revenexx.models.Error]
     */
    suspend fun templateVersionIndex(
        templateId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{templateId}/versions"
            .replace("{templateId}", templateId)

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
     * Addressed by its VERSION NUMBER — the small integer on the history row,
     * not the snapshot's id — because that is the number an author has in front
     * of them.
     * 
     * This is what sends actually rendered while that version was live, so it
     * is the thing to read when the question is "what did the mail we sent in
     *      * March say".
     *
     * @param templateId 
     * @param version 
     * @return [com.revenexx.models.Error]
     */
    suspend fun templateVersionShow(
        templateId: String,
        version: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{templateId}/versions/{version}"
            .replace("{templateId}", templateId)
            .replace("{version}", version)

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
     * `publish: true` makes it live in the same transaction — see
     * TemplatePublisher::restore for why that flag exists rather than asking
     * the caller for a second round trip.
     *
     * @param templateId 
     * @param version 
     * @param publish 
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun templateVersionRestore(
        templateId: String,
        version: String,
        publish: Boolean? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/messaging/templates/{templateId}/versions/{version}/restore"
            .replace("{templateId}", templateId)
            .replace("{version}", version)

        val apiParams = mutableMapOf<String, Any?>(
            "publish" to publish,
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