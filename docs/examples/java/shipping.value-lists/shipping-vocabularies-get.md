```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingValueLists;
import com.revenexx.enums.ShippingVocabulariesGetName;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingValueLists shippingValueLists = new ShippingValueLists(client);

shippingValueLists.shippingVocabulariesGet(
    ShippingVocabulariesGetName.CARRIER_STATUSES, // name 
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
