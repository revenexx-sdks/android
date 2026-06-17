```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Customers
import com.revenexx.enums.ContactRole
import com.revenexx.enums.ContactStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customers = Customers(client)

val result = customers.customersContactsUpdate(
    id = "", 
    email = "", // (optional)
    first_name = "", // (optional)
    is_primary = false, // (optional)
    last_name = "", // (optional)
    locale = "", // (optional)
    organization_id = "", // (optional)
    phone = "", // (optional)
    role = ContactRole.BUYER, // (optional)
    status = ContactStatus.INVITED, // (optional)
)
```
