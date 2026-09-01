```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Markets;
import com.revenexx.enums.MarketStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Markets markets = new Markets(client);

markets.marketsCreate(
    "northwind", // code 
    "Northwind", // name 
    "EUR", // currency (optional)
    false, // is_default (optional)
    Map.of(
        "de-DE", "Nordwind",
        "en-GB", "Northwind"
    ), // labels (optional)
    0, // position (optional)
    MarketStatus.ACTIVE, // status (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
