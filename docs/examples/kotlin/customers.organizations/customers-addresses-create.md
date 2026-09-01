```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersOrganizations

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersOrganizations = CustomersOrganizations(client)

val result = customersOrganizations.customersAddressesCreate(
    city = "Berlin", 
    country = "DE", 
    street = "Musterstraße 12", 
    zip = "10115", 
    company = "Beispiel Industrietechnik GmbH", // (optional)
    contact_id = "", // (optional)
    is_default = true, // (optional)
    name = "Anna Berger", // (optional)
    organization_id = "", // (optional)
    phone = "+49 30 5550123", // (optional)
    region = "Berlin", // (optional)
    street2 = "Gebäude C, 2. OG", // (optional)
    type = "shipping", // (optional)
)
```
