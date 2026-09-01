```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Apps
import com.revenexx.enums.Method

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val apps = Apps(client)

val result = apps.appsCreateExecution(
    functionId = "", 
    async = true, // (optional)
    body = "", // (optional)
    headers = mapOf( "a" to "b" ), // (optional)
    method = method.GET, // (optional)
    path = "/", // (optional)
    scheduledAt = "", // (optional)
)
```
