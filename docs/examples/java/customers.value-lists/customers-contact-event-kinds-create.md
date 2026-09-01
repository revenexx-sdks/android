```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersValueLists;
import com.revenexx.enums.Tone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersValueLists customersValueLists = new CustomersValueLists(client);

customersValueLists.customersContactEventKindsCreate(
    "", // code 
    "Phone call", // title 
    "Somebody spoke to this person on the phone.", // description (optional)
    Map.of(
        "de", "Es wurde mit dieser Person telefoniert.",
        "en", "Somebody spoke to this person on the phone."
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Telefonat",
        "en", "Phone call"
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
