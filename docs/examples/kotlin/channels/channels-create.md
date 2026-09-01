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

val result = channels.channelsCreate(
    code = "shop", 
    name = "Shop", 
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Shop",
        "en" to "Shop"
    ), // (optional)
    position = 1, // (optional)
    status = ChannelStatus.ACTIVE, // (optional)
    type = "storefront", // (optional)
    unassigned_visibility = ChannelUnassignedVisibility.INHERIT, // (optional)
)
```
