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

prices.pricesListsUpdate(
    "", // id 
    "", // channel_id (optional)
    "dealer-de", // code (optional)
    "", // contact_id (optional)
    "EUR", // currency (optional)
    "Contract prices for authorised dealers.", // description (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Händlerpreise",
        "en", "Dealer prices"
    ), // labels (optional)
    Map.of(
        "erp_price_group", "A1",
        "source_system", "erp"
    ), // metadata (optional)
    "Dealer prices", // name (optional)
    "", // organization_id (optional)
    1, // priority (optional)
    true, // requires_auth (optional)
    PriceListStatus.ACTIVE, // status (optional)
    PriceListTaxBasis.NET, // tax_basis (optional)
    true, // tax_included (optional)
    "2026-01-01T00:00:00Z", // valid_from (optional)
    "2026-12-31T23:59:59Z", // valid_until (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
