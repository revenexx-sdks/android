```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersSegments
import com.revenexx.enums.SegmentRuleMatch

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersSegments = CustomersSegments(client)

val result = customersSegments.customersSegmentsUpdate(
    id = "", 
    code = "key_accounts", // (optional)
    labels = mapOf(
        "de" to "Großkunden",
        "en" to "Key accounts"
    ), // (optional)
    position = 1, // (optional)
    rule_match = SegmentRuleMatch.ALL, // (optional)
    rules = mapOf( "a" to "b" ), // (optional)
)
```
