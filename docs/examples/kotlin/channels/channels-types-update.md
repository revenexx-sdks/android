```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Channels
import com.revenexx.enums.ChannelTypeTone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val channels = Channels(client)

val result = channels.channelsTypesUpdate(
    id = "", 
    description = "A web shop a human browses.", // (optional)
    descriptions = mapOf(
        "de" to "Shop",
        "en" to "Shop"
    ), // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Shop",
        "en" to "Shop"
    ), // (optional)
    position = 1, // (optional)
    title = "Product feed", // (optional)
    tone = ChannelTypeTone.NEUTRAL, // (optional)
)
```
