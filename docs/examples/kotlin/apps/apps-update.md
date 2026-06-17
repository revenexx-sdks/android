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
    commands = "", // (optional)
    enabled = false, // (optional)
    entrypoint = "", // (optional)
    events = listOf(), // (optional)
    execute = listOf(), // (optional)
    installationId = "", // (optional)
    logging = false, // (optional)
    providerBranch = "", // (optional)
    providerRepositoryId = "", // (optional)
    providerRootDirectory = "", // (optional)
    providerSilentMode = false, // (optional)
    runtime = runtime.NODE_18_0, // (optional)
    schedule = "", // (optional)
    scopes = scopes.SESSIONS_WRITE, // (optional)
    specification = "", // (optional)
    timeout = 0, // (optional)
)
```
