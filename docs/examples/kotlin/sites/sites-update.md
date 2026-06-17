```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Sites
import com.revenexx.enums.Framework
import com.revenexx.enums.Adapter
import com.revenexx.enums.BuildRuntime

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val sites = Sites(client)

val result = sites.sitesUpdate(
    siteId = "", 
    framework = framework.ANALOG,
    name = "", 
    adapter = adapter.STATIC, // (optional)
    buildCommand = "", // (optional)
    buildRuntime = buildRuntime.NODE_18_0, // (optional)
    enabled = false, // (optional)
    fallbackFile = "", // (optional)
    installCommand = "", // (optional)
    installationId = "", // (optional)
    logging = false, // (optional)
    outputDirectory = "", // (optional)
    providerBranch = "", // (optional)
    providerRepositoryId = "", // (optional)
    providerRootDirectory = "", // (optional)
    providerSilentMode = false, // (optional)
    specification = "", // (optional)
    timeout = 0, // (optional)
)
```
