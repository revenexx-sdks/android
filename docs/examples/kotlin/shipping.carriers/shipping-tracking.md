```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingCarriers

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingCarriers = ShippingCarriers(client)

val result = shippingCarriers.shippingTracking(
    carrier = "acme-parcel", 
    country = "DE", // (optional)
    postal_code = "12345", // (optional)
    tracking_code = "ACME000000001DE", // (optional)
)
```
