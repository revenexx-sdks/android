```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging
import com.revenexx.enums.ResourceType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.auditIndex(
    resource_type = resource_type.TEMPLATE, // (optional)
    resource_id = "", // (optional)
    subject = "", // (optional)
    limit = 1, // (optional)
)
```
