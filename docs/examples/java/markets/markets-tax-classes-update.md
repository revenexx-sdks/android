```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Markets;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Markets markets = new Markets(client);

markets.marketsTaxClassesUpdate(
    "", // market_id 
    "", // id 
    "standard", // code (optional)
    true, // is_default (optional)
    Map.of(
        "de-DE", "Regelsatz",
        "en-GB", "Standard rate"
    ), // labels (optional)
    "Standard rate", // name (optional)
    0, // position (optional)
    20, // rate (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
