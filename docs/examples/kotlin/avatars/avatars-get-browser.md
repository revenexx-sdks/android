```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Avatars
import com.revenexx.enums.Code

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val avatars = Avatars(client)

val result = avatars.avatarsGetBrowser(
    code = code.AA,
    width = 0, // (optional)
    height = 0, // (optional)
    quality = 0, // (optional)
)
```
