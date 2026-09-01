```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersOrganizations;
import com.revenexx.enums.OrganizationStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersOrganizations customersOrganizations = new CustomersOrganizations(client);

customersOrganizations.customersOrganizationsCreate(
    "Beispiel Industrietechnik GmbH", // name 
    "Maschinenbau", // branche (optional)
    5000, // credit_limit (optional)
    "K-10042", // customer_number (optional)
    true, // delivery_block (optional)
    "customer", // lifecycle_stage (optional)
    "net_30", // payment_terms (optional)
    "standard", // price_list (optional)
    Map.of(
        "account_manager", "sales-north",
        "delivery_tour", "tuesday",
        "self_pickup", true
    ), // settings (optional)
    OrganizationStatus.ACTIVE, // status (optional)
    "DE123456789", // vat_id (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
