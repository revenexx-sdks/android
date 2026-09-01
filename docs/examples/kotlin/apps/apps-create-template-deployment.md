```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Apps
import com.revenexx.enums.Type

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val apps = Apps(client)

val result = apps.appsCreateTemplateDeployment(
    functionId = "", 
    owner = "", 
    reference = "", 
    repository = "", 
    rootDirectory = "", 
    type = type.COMMIT,
    activate = true, // (optional)
)
```
