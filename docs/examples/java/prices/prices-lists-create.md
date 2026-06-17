```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceListStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesListsCreate(
    "", // code 
    "", // name 
    "", // channel_id (optional)
    "", // contact_id (optional)
    "", // currency (optional)
    "", // description (optional)
    false, // is_default (optional)
    Map.of("a", "b"), // labels (optional)
    "", // market_id (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // organization_id (optional)
    0, // priority (optional)
    PriceListStatus.ACTIVE, // status (optional)
    false, // tax_included (optional)
    "", // valid_from (optional)
    "", // valid_until (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
