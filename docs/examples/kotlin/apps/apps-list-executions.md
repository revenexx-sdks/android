```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Apps

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val apps = Apps(client)

val result = apps.appsListExecutions(
    functionId = "", 
    queries = listOf(), // (optional)
    total = true, // (optional)
)
```
