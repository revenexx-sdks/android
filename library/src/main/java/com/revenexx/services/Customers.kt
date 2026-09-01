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
 * Storefront access: the authentication passthrough a shop front-end calls, and the principal resolver the API gateway calls. Register, log in, log out, recover a password, resolve the current session back to its contact — this app owns the customer DATA while the platform identity service owns the sessions, so these routes forward to it and answer with both halves. Session material travels in the body, which makes the expected caller a trusted BFF rather than a browser. These are the only operations here with no Cockpit screen; every group below is one.
 */
class Customers(client: Client) : Service(client) {

    /**
     * An email and a password go in; a session and the CONTACT behind it come back, so a storefront knows in one call both that the buyer is signed in and who they are. The session is minted server-side rather than handed back from the credential check, because the account route hides the session secret from non-privileged responses and a trusted BFF needs it. `permissions` carries the buyer's effective grants, so a BFF does not need a second call to decide what to render.
     *
     * @param email The buyer's login address — the same one the contact carries.
     * @param password The password from registration or recovery. Wrong credentials are a 401; a correct one on an undecided application is a 403.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthLogin(
        email: String,
        password: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/login"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "password" to password,
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
     * Ends ONE session — the buyer signs out on this device and stays signed in on the others, because the session id is what is revoked and not the account. The contact row is untouched: signing out is not blocking, and a caller wanting the second thing wants `status: "blocked"` on the contact instead. Both ids come from what `/customers/auth/login` answered, and a BFF should drop its own cookie whatever this answers — the session is unusable afterwards either way.
     *
     * @param sessionId The session to revoke — `session.$id` from the login.
     * @param userId The platform user — `session.userId` from the login.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthLogout(
        sessionId: String,
        userId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/logout"

        val apiParams = mutableMapOf<String, Any?>(
            "session_id" to sessionId,
            "user_id" to userId,
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
     * Sign in without a password: a link goes to the address, and `PUT /customers/auth/magic-link` turns it into a session. Creates the account when the address is new, which makes this a registration path as much as a sign-in one — and why an address nobody holds is not distinguished in the answer. The mail is this shop's own template through the messaging service; the secret is not in this response, only in the link.
     *
     * @param email Who to send the link to. An address that has never been seen creates an account rather than failing.
     * @param url Where the mailed link points. `userId`, `secret` and `expire` are appended as query parameters; the first two are what the confirm call takes.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthMagicLink(
        email: String,
        url: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/magic-link"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "url" to url,
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
     * The buyer clicked the link and the storefront read `userId` and `secret` out of it. Answers exactly what a password login answers — session, contact and effective grants — because a shop must not have to branch on how somebody signed in.
     *
     * @param secret The one-time secret the mailed link carried. Spent on first use and expiring, so a second attempt with the same one is a 401 rather than a second session.
     * @param userId The `userId` the mailed link carried.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthMagicLinkConfirm(
        secret: String,
        userId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/magic-link"

        val apiParams = mutableMapOf<String, Any?>(
            "secret" to secret,
            "user_id" to userId,
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
     * The platform user, the customer record mirrored against it and the effective grants, in one call. The expected caller is a trusted storefront BFF holding the session on the buyer's behalf, which is why the ids travel in the body rather than in a browser-facing header. The grants are derived here on every call rather than returned from anywhere they could be cached, so a role changed a second ago is already reflected.
     *
     * @param userId The platform user to resolve — `session.userId` from the login.
     * @param sessionId Optional session to verify. Pass it to ask "is this session still alive?" (a revoked one is then a 401); omit it to only ask who a user is.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAuthMe(
        userId: String,
        sessionId: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/me"

        val apiParams = mutableMapOf<String, Any?>(
            "session_id" to sessionId,
            "user_id" to userId,
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
     * Between the password and the finished session: the buyer has proved one thing and is asked for another. Created by user id, because the account route that creates challenges hides the code from whoever may call it — and answered with the half-finished session the sign-in is in the middle of, through `PUT /customers/auth/mfa/challenge`. Needs a platform build that returns the challenge code; without one there is no way to read what to send, and the call answers 502 rather than mailing an empty challenge.
     *
     * @param userId The platform user being challenged.
     * @param factor Which factor to challenge. Defaults to `email`, the only one this route mails.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAuthMfaChallenge(
        userId: String,
        factor: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/mfa/challenge"

        val apiParams = mutableMapOf<String, Any?>(
            "factor" to factor,
            "user_id" to userId,
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
     * The code the buyer typed, against the challenge it was sent for. The session becomes fully authenticated when this answers.
     *
     * @param challengeId The `$id` the send answered with.
     * @param code What the buyer typed.
     * @param sessionSecret The same session the challenge was created with.
     * @param userId The platform user, for the caller's own bookkeeping. The challenge already knows whose it is.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAuthMfaChallengeConfirm(
        challengeId: String,
        code: String,
        sessionSecret: String,
        userId: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/mfa/challenge"

        val apiParams = mutableMapOf<String, Any?>(
            "challenge_id" to challengeId,
            "code" to code,
            "session_secret" to sessionSecret,
            "user_id" to userId,
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
     * The same token as the sign-in link, delivered as a short code instead — for a buyer on a phone, where leaving for a mail client and coming back loses the checkout they were in the middle of. Redeemed with `PUT /customers/auth/otp`.
     *
     * @param email Who to send the code to. As with the sign-in link, an unknown address creates an account rather than failing.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthOtp(
        email: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/otp"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
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
     * The code the buyer typed, plus the `userId` the send answered with. Answers exactly what a password login answers — session, contact and effective grants — so a storefront never has to branch on how somebody signed in. The code is spent on first use and expires, so a second attempt with the same one is a 401 rather than a second session.
     *
     * @param secret The one-time secret the mailed code carried. Spent on first use and expiring, so a second attempt with the same one is a 401 rather than a second session.
     * @param userId The `userId` the mailed code carried.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthOtpConfirm(
        secret: String,
        userId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/otp"

        val apiParams = mutableMapOf<String, Any?>(
            "secret" to secret,
            "user_id" to userId,
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
     * Step one of two: a link goes to the address given, and `PUT /customers/auth/recovery` is what the buyer's browser comes back to. The identity service mints the token; the MAIL is this shop's own — the tenant's template, layout, language and sending domain, through the messaging service. The secret is NOT in this answer: it exists only inside the mailed link, which is the whole point of the two-step shape, and echoing it here would make the mail decorative. Nothing about the contact changes; the password only moves in step two.
     *
     * @param email Who to send the recovery mail to. An address nobody holds is not distinguished here — do not build an account-existence check on the answer.
     * @param url Where the mailed link points. `userId`, `secret` and `expire` are appended as query parameters — the first two are what the confirm call takes. Same shape the identity service's own mail used, so a storefront that already handles that link needs no change.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthRecovery(
        email: String,
        url: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "url" to url,
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
     * Step two: the `userId` and `secret` the mailed link carried, plus the password the buyer just typed. The secret is spent on first use and expires, so a link cannot be replayed and a second attempt with the same one is a 401 rather than a second password change. The new password is in effect the moment this answers; what happens to sessions opened with the old one is the identity service's policy, not this app's.
     *
     * @param password The new password. It replaces the old one immediately; existing sessions are the identity service's business, not this app's.
     * @param secret The one-time secret from the mailed link. Only that value works — it is spent on first use and expires, and anything else is a 401, so no example here would be anything but a call that fails.
     * @param userId The `userId` the mailed link carried.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthRecoveryConfirm(
        password: String,
        secret: String,
        userId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "password" to password,
            "secret" to secret,
            "user_id" to userId,
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
     * One call writes the whole buyer: the contact this app is the system of record for, and the platform user behind its login. When the body names a company it also FOUNDS one — an organization, mirrored into platform auth as a team, with this contact as its admin. The tenant setting registration_mode decides what a registration IS. 'open' (the default, unchanged behaviour) creates a finished account: registration_status='approved', status='active', login works. 'approval_required' creates an APPLICATION: registration_status='pending', status='invited', the platform user exists with the applicant's own password but is DISABLED, and a newly founded organization is parked as 'blocked' — check `approval_required` in the response and show a 'we will get back to you' screen instead of logging the buyer in. The registration gates below are all evaluated BEFORE anything is written, and a failure after that point rolls the organization and the contact back together.
     *
     * @param email The buyer's address. It becomes the login AND the unique key of the contact, so a second registration with it is a 409 — including while the first one is still waiting for approval.
     * @param password The password the buyer chooses. It is hashed by the identity service at this moment and never travels again: an approval later enables the account, it does not issue a new credential.
     * @param firstName Given name. Optional: an ERP import often has only a mailbox.
     * @param lastName Family name. Optional for the same reason.
     * @param locale The language this person is written to in — BCP 47, and one of the store's configured locales. Null falls back to the store default. One of the store's own locales, or the call is a 400.
     * @param organizationId JOIN an existing company — the invite shape. Neither b2b_registration_enabled nor b2c_registration_enabled applies to it.
     * @param organizationName FOUND a new company, with this contact as its admin. This is what makes the registration a B2B one; leaving it out registers a standalone buyer.
     * @param url Where the welcome mail's button points — the buyer's first stop in this shop. Absent, the mail still goes out and simply carries no button. Ignored when the registration is an APPLICATION: there is no account to send anybody to yet.
     * @param vatId VAT identification number (USt-IdNr. in Germany) — the closest thing a B2B buyer has to a legal identity. Validated against the EU VIES service when the tenant's `organization_vat_id_required` setting is on, and stored verbatim otherwise, including for buyers outside the EU. Required when the tenant's `organization_vat_id_required` is on, and checked BEFORE the company is created so a bad one leaves no half-founded organization behind.
     * @param verificationUrl Where the address-confirmation link points, when the tenant's `email_verification` asks for one on registration. `userId`, `secret` and `expire` are appended, and `PUT /customers/auth/verification` takes the first two. Without it the registration still succeeds and `verification_sent` is false — this app cannot invent a storefront URL, and a link pointing nowhere is worse than none.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAuthRegister(
        email: String,
        password: String,
        firstName: String? = null,
        lastName: String? = null,
        locale: String? = null,
        organizationId: String? = null,
        organizationName: String? = null,
        url: String? = null,
        vatId: String? = null,
        verificationUrl: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/register"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "first_name" to firstName,
            "last_name" to lastName,
            "locale" to locale,
            "organization_id" to organizationId,
            "organization_name" to organizationName,
            "password" to password,
            "url" to url,
            "vat_id" to vatId,
            "verification_url" to verificationUrl,
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
     * Confirm that the address belongs to the buyer. Needs no session: the verification is created through the identity service's users surface, because its account counterpart reads the authenticated user and a caller authenticating AS the user cannot see the secret it just created. The buyer still confirms with their own session, through `PUT /customers/auth/verification` — only the creation moved. Send it right after a registration, or from an account page.
     *
     * @param url Where the mailed link points. `userId`, `secret` and `expire` are appended as query parameters; the first two are what the confirm call takes.
     * @param userId The platform user whose address is being confirmed — `user_id` from the registration, or `session.userId` from a login.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthVerification(
        url: String,
        userId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/verification"

        val apiParams = mutableMapOf<String, Any?>(
            "url" to url,
            "user_id" to userId,
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
     * The `userId` and `secret` the mailed link carried. The address counts as confirmed the moment this answers; the secret is spent, so the link cannot be replayed.
     *
     * @param secret The one-time secret the mailed link carried. Spent on first use and expiring, so a second attempt with the same one is a 401 rather than a second session.
     * @param userId The `userId` the mailed link carried.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAuthVerificationConfirm(
        secret: String,
        userId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/auth/verification"

        val apiParams = mutableMapOf<String, Any?>(
            "secret" to secret,
            "user_id" to userId,
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
     * The capability the API gateway calls to turn a caller's X-Revenexx-Principal assertion into the permission set it forwards to every other app as X-Revenexx-Permissions. This app is the platform's role provider (manifest#provides_roles), and this is the hot path of every attributed storefront request — one contact read plus the tenant's role map. A blocked or pending contact always resolves with active=false; what its `permissions` then say is the tenant's blocked_contact_behavior setting — 'keep' (the default, the role's grants), 'catalog_only' or 'deny_all'.
     *
     * @param contactId The contact the caller is acting for.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersPrincipalResolve(
        contactId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/principal/resolve"

        val apiParams = mutableMapOf<String, Any?>(
            "contact_id" to contactId,
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