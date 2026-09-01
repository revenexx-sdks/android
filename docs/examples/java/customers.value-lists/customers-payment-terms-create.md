```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersValueLists;
import com.revenexx.enums.Tone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersValueLists customersValueLists = new CustomersValueLists(client);

customersValueLists.customersPaymentTermsCreate(
    "", // code 
    "Net 30 days", // title 
    "Invoice due 30 days after the delivery note.", // description (optional)
    Map.of(
        "de", "Rechnung 30 Tage nach Lieferschein fällig.",
        "en", "Invoice due 30 days after the delivery note."
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Zahlbar in 30 Tagen",
        "en", "Net 30 days"
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
