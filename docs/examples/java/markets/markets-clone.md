```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Markets;
import com.revenexx.enums.MarketStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Markets markets = new Markets(client);

markets.marketsClone(
    "northwind", // id 
    "northwind-b2b", // code 
    true, // copy_currencies (optional)
    true, // copy_locales (optional)
    true, // copy_tax_classes (optional)
    "EUR", // currency (optional)
    "Northwind B2B", // name (optional)
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
