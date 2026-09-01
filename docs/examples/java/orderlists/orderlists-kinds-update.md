```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orderlists;
import com.revenexx.enums.OrderListKindTone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orderlists orderlists = new Orderlists(client);

orderlists.orderlistsKindsUpdate(
    "", // id 
    "Chemicals ordered against a standing lab protocol.", // description (optional)
    Map.of(
        "de", "Chemikalien, die nach einem festen Laborprotokoll bestellt werden.",
        "en", "Chemicals ordered against a standing lab protocol."
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Reagenzienliste",
        "en", "Reagent list"
    ), // labels (optional)
    2, // position (optional)
    "Reagent list", // title (optional)
    OrderListKindTone.NEUTRAL, // tone (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
