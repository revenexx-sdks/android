```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Channels
import com.revenexx.enums.ChannelStatus
import com.revenexx.enums.ChannelType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val channels = Channels(client)

val result = channels.channelsCreate(
    code = "", 
    name = "", 
    is_default = false, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    position = 0, // (optional)
    status = ChannelStatus.ACTIVE, // (optional)
    type = ChannelType.STOREFRONT, // (optional)
)
```
