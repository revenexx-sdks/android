```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceListStatus;
import com.revenexx.enums.PriceListTaxBasis;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesListsList(
    "", // id (optional)
    "standard", // code (optional)
    "Standard prices", // name (optional)
    "The list every buyer falls back to.", // description (optional)
    "EUR", // currency (optional)
    PriceListStatus.ACTIVE, // status (optional)
    1, // priority (optional)
    true, // is_default (optional)
    PriceListTaxBasis.NET, // tax_basis (optional)
    true, // tax_included (optional)
    true, // requires_auth (optional)
    "", // contact_id (optional)
    "", // organization_id (optional)
    "", // channel_id (optional)
    "2026-01-01T12:00:00Z", // valid_from (optional)
    "2026-01-01T12:00:00Z", // valid_until (optional)
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
