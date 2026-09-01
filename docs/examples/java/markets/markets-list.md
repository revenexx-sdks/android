```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Markets;
import com.revenexx.enums.MarketsListStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Markets markets = new Markets(client);

markets.marketsList(
    "", // id (optional)
    "northwind", // code (optional)
    "Northwind", // name (optional)
    "{"de-DE":"Nordwind","en-GB":"Northwind"}", // labels (optional)
    "EUR", // currency (optional)
    MarketsListStatus.ACTIVE, // status (optional)
    false, // is_default (optional)
    0, // position (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    50, // limit (optional)
    0, // offset (optional)
    "position.asc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
