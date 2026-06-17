```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Shipping
import com.revenexx.enums.ShippingMethodMatrixBasis
import com.revenexx.enums.ShippingMethodPricingType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shipping = Shipping(client)

val result = shipping.shippingMethodsCreate(
    code = "", 
    name = "", 
    carrier = "", // (optional)
    countries = listOf(), // (optional)
    currency = "", // (optional)
    description = "", // (optional)
    enabled = false, // (optional)
    eta_days_max = 0, // (optional)
    eta_days_min = 0, // (optional)
    free_above = 0, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    matrix_attribute = "", // (optional)
    matrix_basis = ShippingMethodMatrixBasis.WEIGHT, // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    position = 0, // (optional)
    price = 0, // (optional)
    pricing_type = ShippingMethodPricingType.FIXED, // (optional)
)
```
