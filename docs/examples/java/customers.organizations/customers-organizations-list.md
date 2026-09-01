```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersOrganizations;
import com.revenexx.enums.CustomersOrganizationsListStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersOrganizations customersOrganizations = new CustomersOrganizations(client);

customersOrganizations.customersOrganizationsList(
    "", // id (optional)
    "Beispiel Industrietechnik GmbH", // name (optional)
    "DE123456789", // vat_id (optional)
    "Maschinenbau", // branche (optional)
    "K-10042", // customer_number (optional)
    CustomersOrganizationsListStatus.ACTIVE, // status (optional)
    "customer", // lifecycle_stage (optional)
    "net_30", // payment_terms (optional)
    9.99, // credit_limit (optional)
    "standard", // price_list (optional)
    true, // delivery_block (optional)
    "", // external_team_id (optional)
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
