```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingCarriers
import com.revenexx.enums.ShippingCarriersListStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingCarriers = ShippingCarriers(client)

val result = shippingCarriers.shippingCarriersList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "position.asc", // (optional)
    code = "acme-parcel", // (optional)
    status = Shipping.carriers.listStatus.ACTIVE, // (optional)
    service_level = "express", // (optional)
)
```
