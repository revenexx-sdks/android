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
    buildCommand = "npm run build", // (optional)
    buildRuntime = buildRuntime.NODE_18_0, // (optional)
    enabled = true, // (optional)
    fallbackFile = "index.html", // (optional)
    installCommand = "npm install", // (optional)
    installationId = "", // (optional)
    logging = true, // (optional)
    outputDirectory = "", // (optional)
    providerBranch = "main", // (optional)
    providerRepositoryId = "", // (optional)
    providerRootDirectory = "", // (optional)
    providerSilentMode = true, // (optional)
    specification = "s-1vcpu-512mb", // (optional)
    timeout = 1, // (optional)
)
```
