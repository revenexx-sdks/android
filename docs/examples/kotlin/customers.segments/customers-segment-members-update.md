```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersSegments
import com.revenexx.enums.SegmentMemberSource

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersSegments = CustomersSegments(client)

val result = customersSegments.customersSegmentMembersUpdate(
    id = "", 
    organization_id = "", // (optional)
    segment_id = "", // (optional)
    source = SegmentMemberSource.MANUAL, // (optional)
)
```
