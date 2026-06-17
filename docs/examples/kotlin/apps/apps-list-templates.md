```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Apps
import com.revenexx.enums.Runtimes
import com.revenexx.enums.UseCases

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val apps = Apps(client)

val result = apps.appsListTemplates(
    runtimes = runtimes.NODE_18_0, // (optional)
    useCases = useCases.STARTER, // (optional)
    limit = 0, // (optional)
    offset = 0, // (optional)
    total = false, // (optional)
)
```
