```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Markets;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Markets markets = new Markets(client);

markets.marketsLocalesUpdate(
    "", // market_id 
    "", // id 
    "de-DE", // code (optional)
    "DE", // country (optional)
    true, // is_default (optional)
    "de", // language (optional)
    0, // position (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
