```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PagesDelivery

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pagesDelivery = PagesDelivery(client)

val result = pagesDelivery.pagesDeliveryPage(
    slug = "about-us", // (optional)
    id = "", // (optional)
    langcode = "de", // (optional)
)
```
