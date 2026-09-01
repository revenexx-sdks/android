```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.bindingStore(
    channel = "", 
    event_topic = "", 
    recipient = "", 
    template_key = "", 
    enabled = true, // (optional)
    fallback_order = 1, // (optional)
    locale = "", // (optional)
)
```
