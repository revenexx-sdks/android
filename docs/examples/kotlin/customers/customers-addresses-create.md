```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Customers
import com.revenexx.enums.AddressType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customers = Customers(client)

val result = customers.customersAddressesCreate(
    city = "", 
    country = "", 
    street = "", 
    zip = "", 
    company = "", // (optional)
    contact_id = "", // (optional)
    is_default = false, // (optional)
    name = "", // (optional)
    organization_id = "", // (optional)
    phone = "", // (optional)
    region = "", // (optional)
    street2 = "", // (optional)
    type = AddressType.BILLING, // (optional)
)
```
