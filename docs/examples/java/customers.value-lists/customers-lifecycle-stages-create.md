```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersValueLists;
import com.revenexx.enums.Tone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersValueLists customersValueLists = new CustomersValueLists(client);

customersValueLists.customersLifecycleStagesCreate(
    "", // code 
    "Customer", // title 
    "Has ordered at least once and is being served.", // description (optional)
    Map.of(
        "de", "Hat mindestens einmal bestellt und wird betreut.",
        "en", "Has ordered at least once and is being served."
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Kunde",
        "en", "Customer"
    ), // labels (optional)
    1, // position (optional)
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
