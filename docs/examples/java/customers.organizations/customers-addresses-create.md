```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersOrganizations;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersOrganizations customersOrganizations = new CustomersOrganizations(client);

customersOrganizations.customersAddressesCreate(
    "Berlin", // city 
    "DE", // country 
    "Musterstraße 12", // street 
    "10115", // zip 
    "Beispiel Industrietechnik GmbH", // company (optional)
    "", // contact_id (optional)
    true, // is_default (optional)
    "Anna Berger", // name (optional)
    "", // organization_id (optional)
    "+49 30 5550123", // phone (optional)
    "Berlin", // region (optional)
    "Gebäude C, 2. OG", // street2 (optional)
    "shipping", // type (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
