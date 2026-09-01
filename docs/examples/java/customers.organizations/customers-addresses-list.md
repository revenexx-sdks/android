```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersOrganizations;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersOrganizations customersOrganizations = new CustomersOrganizations(client);

customersOrganizations.customersAddressesList(
    "", // id (optional)
    "", // organization_id (optional)
    "", // contact_id (optional)
    "shipping", // type (optional)
    "Beispiel Industrietechnik GmbH", // company (optional)
    "Anna Berger", // name (optional)
    "Musterstraße 12", // street (optional)
    "Gebäude C, 2. OG", // street2 (optional)
    "10115", // zip (optional)
    "Berlin", // city (optional)
    "Berlin", // region (optional)
    "DE", // country (optional)
    "+49 30 5550123", // phone (optional)
    true, // is_default (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
