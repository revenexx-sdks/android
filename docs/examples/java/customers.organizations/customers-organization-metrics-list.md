```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersOrganizations;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersOrganizations customersOrganizations = new CustomersOrganizations(client);

customersOrganizations.customersOrganizationMetricsList(
    "", // id (optional)
    "", // organization_id (optional)
    1, // order_count (optional)
    1, // order_count_30d (optional)
    1, // order_count_90d (optional)
    1, // order_count_365d (optional)
    9.99, // revenue_total (optional)
    9.99, // revenue_30d (optional)
    9.99, // revenue_90d (optional)
    9.99, // revenue_365d (optional)
    9.99, // avg_order_value (optional)
    9.99, // avg_order_value_365d (optional)
    "2026-01-01T12:00:00Z", // first_order_at (optional)
    "2026-01-01T12:00:00Z", // last_order_at (optional)
    "EUR", // currency (optional)
    true, // currency_mixed (optional)
    "2026-01-01T12:00:00Z", // orders_as_of (optional)
    "2026-01-01T12:00:00Z", // computed_at (optional)
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
