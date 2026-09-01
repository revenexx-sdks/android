```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersValueLists;
import com.revenexx.enums.Tone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersValueLists customersValueLists = new CustomersValueLists(client);

customersValueLists.customersAddressTypesUpdate(
    "", // id 
    "Where the goods go.", // description (optional)
    Map.of(
        "de", "Wohin die Ware geliefert wird.",
        "en", "Where the goods go."
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Lieferadresse",
        "en", "Shipping address"
    ), // labels (optional)
    1, // position (optional)
    "Shipping address", // title (optional)
    Tone.NEUTRAL, // tone (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
