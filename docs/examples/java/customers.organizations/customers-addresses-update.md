```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersOrganizations;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersOrganizations customersOrganizations = new CustomersOrganizations(client);

customersOrganizations.customersAddressesUpdate(
    "", // id 
    "Berlin", // city (optional)
    "Beispiel Industrietechnik GmbH", // company (optional)
    "", // contact_id (optional)
    "DE", // country (optional)
    true, // is_default (optional)
    "Anna Berger", // name (optional)
    "", // organization_id (optional)
    "+49 30 5550123", // phone (optional)
    "Berlin", // region (optional)
    "Musterstraße 12", // street (optional)
    "Gebäude C, 2. OG", // street2 (optional)
    "shipping", // type (optional)
    "10115", // zip (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
