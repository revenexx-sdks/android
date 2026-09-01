```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingCarriers
import com.revenexx.enums.ShippingCarrierStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingCarriers = ShippingCarriers(client)

val result = shippingCarriers.shippingCarriersUpdate(
    id = "", 
    code = "acme-parcel", // (optional)
    countries = listOf("DE", "AT", "CH"), // (optional)
    cutoff_time = "16:00", // (optional)
    eta_days_max = 1, // (optional)
    eta_days_min = 1, // (optional)
    handling_days = 1, // (optional)
    labels = mapOf(
        "de" to "Acme Paketdienst",
        "en" to "Acme Parcel"
    ), // (optional)
    metadata = mapOf(
        "contract" to "ACME-2026",
        "customer_number" to "4711"
    ), // (optional)
    name = "Acme Parcel", // (optional)
    position = 1, // (optional)
    service_level = "express", // (optional)
    status = ShippingCarrierStatus.ACTIVE, // (optional)
    tracking_url_template = "https://track.example.com/parcels/{tracking_code}", // (optional)
)
```
