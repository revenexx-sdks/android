```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Apps
import com.revenexx.enums.Runtime
import com.revenexx.enums.Scopes

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val apps = Apps(client)

val result = apps.appsUpdate(
    functionId = "", 
    name = "", 
    commands = "npm install", // (optional)
    enabled = true, // (optional)
    entrypoint = "src/main.js", // (optional)
    events = listOf(), // (optional)
    execute = listOf("any"), // (optional)
    installationId = "", // (optional)
    logging = true, // (optional)
    providerBranch = "main", // (optional)
    providerRepositoryId = "", // (optional)
    providerRootDirectory = "", // (optional)
    providerSilentMode = true, // (optional)
    runtime = runtime.NODE_18_0, // (optional)
    schedule = "0 3 * * *", // (optional)
    scopes = scopes.SESSIONS_WRITE, // (optional)
    specification = "s-1vcpu-512mb", // (optional)
    timeout = 1, // (optional)
)
```
