```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging
import com.revenexx.enums.Priority

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.messagingUpdatePush(
    messageId = "", 
    action = "", // (optional)
    badge = 0, // (optional)
    body = "", // (optional)
    color = "", // (optional)
    contentAvailable = false, // (optional)
    critical = false, // (optional)
    data = mapOf( "a" to "b" ), // (optional)
    draft = false, // (optional)
    icon = "", // (optional)
    image = "", // (optional)
    priority = priority.NORMAL, // (optional)
    scheduledAt = "", // (optional)
    sound = "", // (optional)
    tag = "", // (optional)
    targets = listOf(), // (optional)
    title = "", // (optional)
    topics = listOf(), // (optional)
    users = listOf(), // (optional)
)
```
