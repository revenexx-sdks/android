```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersSegments;
import com.revenexx.enums.SegmentRuleMatch;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersSegments customersSegments = new CustomersSegments(client);

customersSegments.customersSegmentsCreate(
    "key_accounts", // code 
    Map.of(
        "de", "Großkunden",
        "en", "Key accounts"
    ), // labels (optional)
    1, // position (optional)
    SegmentRuleMatch.ALL, // rule_match (optional)
    Map.of("a", "b"), // rules (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
