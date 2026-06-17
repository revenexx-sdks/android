```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Sites
import com.revenexx.enums.Type

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val sites = Sites(client)

val result = sites.sitesGetDeploymentDownload(
    siteId = "", 
    deploymentId = "", 
    type = type.SOURCE, // (optional)
)
```
