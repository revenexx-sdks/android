```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Channels
import com.revenexx.enums.ChannelStatus
import com.revenexx.enums.ChannelUnassignedVisibility

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val channels = Channels(client)

val result = channels.channelsList(
    id = "", // (optional)
    code = "shop", // (optional)
    name = "Shop", // (optional)
    labels = "{"en":"Shop","de":"Shop"}", // (optional)
    type = "storefront", // (optional)
    status = ChannelStatus.ACTIVE, // (optional)
    unassigned_visibility = ChannelUnassignedVisibility.INHERIT, // (optional)
    is_default = true, // (optional)
    position = 1, // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
)
```
