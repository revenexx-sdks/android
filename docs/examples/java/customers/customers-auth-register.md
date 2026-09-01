```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Customers;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Customers customers = new Customers(client);

customers.customersAuthRegister(
    "einkauf@example.com", // email 
    "", // password 
    "Anna", // first_name (optional)
    "Berger", // last_name (optional)
    "de-DE", // locale (optional)
    "", // organization_id (optional)
    "Beispiel Industrietechnik GmbH", // organization_name (optional)
    "https://shop.example.com/account", // url (optional)
    "DE123456789", // vat_id (optional)
    "https://shop.example.com/bestaetigen", // verification_url (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
