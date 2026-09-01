```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersSegments;
import com.revenexx.enums.RuleMatch;
import com.revenexx.enums.Target;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersSegments customersSegments = new CustomersSegments(client);

customersSegments.customersSegmentsRulesPreview(
    "", // segment_id 
    List.of(), // conditions 
    RuleMatch.ALL, // rule_match (optional)
    Target.ORGANIZATIONS, // target (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
