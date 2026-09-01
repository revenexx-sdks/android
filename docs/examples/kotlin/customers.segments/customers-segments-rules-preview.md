```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersSegments
import com.revenexx.enums.RuleMatch
import com.revenexx.enums.Target

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersSegments = CustomersSegments(client)

val result = customersSegments.customersSegmentsRulesPreview(
    segment_id = "", 
    conditions = listOf(), 
    rule_match = rule_match.ALL, // (optional)
    target = target.ORGANIZATIONS, // (optional)
)
```
