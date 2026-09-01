```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging
import com.revenexx.enums.MessageClass
import com.revenexx.enums.WhatsappCategory

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.templateUpdate(
    id = "", 
    body_html = "", // (optional)
    body_text = "", // (optional)
    content_sid = "", // (optional)
    design = listOf(), // (optional)
    enabled = true, // (optional)
    layout_id = "", // (optional)
    markets = listOf(), // (optional)
    message_class = message_class.TRANSACTIONAL, // (optional)
    subject = "", // (optional)
    test_mode = true, // (optional)
    title = "", // (optional)
    valid_from = "2026-01-01T12:00:00Z", // (optional)
    valid_until = "2026-01-01T12:00:00Z", // (optional)
    variable_defaults = listOf(), // (optional)
    variables = listOf(), // (optional)
    whatsapp_category = whatsapp_category.MARKETING, // (optional)
)
```
