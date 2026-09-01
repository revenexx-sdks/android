```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Markets;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Markets markets = new Markets(client);

markets.marketsLocalesList(
    "", // market_id 
    "", // id (optional)
    "de-DE", // code (optional)
    "de", // language (optional)
    "DE", // country (optional)
    true, // is_default (optional)
    0, // position (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
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
