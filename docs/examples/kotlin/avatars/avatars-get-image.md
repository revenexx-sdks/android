```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Avatars

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val avatars = Avatars(client)

val result = avatars.avatarsGetImage(
    url = "https://www.revenexx.com/img/hero-revenexx-poster.webp", 
    width = 1, // (optional)
    height = 1, // (optional)
)
```
