```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersSegments;
import com.revenexx.enums.SegmentMemberSource;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersSegments customersSegments = new CustomersSegments(client);

customersSegments.customersSegmentMembersUpdate(
    "", // id 
    "", // organization_id (optional)
    "", // segment_id (optional)
    SegmentMemberSource.MANUAL, // source (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
