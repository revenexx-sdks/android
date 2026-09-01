```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Customers

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customers = Customers(client)

val result = customers.customersAuthRegister(
    email = "einkauf@example.com", 
    password = "", 
    first_name = "Anna", // (optional)
    last_name = "Berger", // (optional)
    locale = "de-DE", // (optional)
    organization_id = "", // (optional)
    organization_name = "Beispiel Industrietechnik GmbH", // (optional)
    url = "https://shop.example.com/account", // (optional)
    vat_id = "DE123456789", // (optional)
    verification_url = "https://shop.example.com/bestaetigen", // (optional)
)
```
