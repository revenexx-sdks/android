```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Io

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val io = Io(client)

val result = io.listBulkJobs(
    type = , // (optional)
    status = , // (optional)
    vendor = "", // (optional)
    app = "", // (optional)
    entity = "", // (optional)
    limit = 1, // (optional)
)
```
