```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Sites
import com.revenexx.enums.BuildRuntime
import com.revenexx.enums.Framework
import com.revenexx.enums.Adapter

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val sites = Sites(client)

val result = sites.sitesCreate(
    buildRuntime = buildRuntime.NODE_18_0,
    framework = framework.ANALOG,
    name = "", 
    siteId = "", 
    adapter = adapter.STATIC, // (optional)
    buildCommand = "npm run build", // (optional)
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
