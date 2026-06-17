```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Sites

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val sites = Sites(client)

val result = sites.sitesUpdateVariable(
    siteId = "", 
    variableId = "", 
    key = "", 
    secret = false, // (optional)
    value = "", // (optional)
)
```
