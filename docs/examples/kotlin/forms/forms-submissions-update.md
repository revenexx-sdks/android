```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Forms
import com.revenexx.enums.FormSubmissionStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val forms = Forms(client)

val result = forms.formsSubmissionsUpdate(
    id = "", 
    data = mapOf(
        "company" to "Example GmbH",
        "email" to "buyer@example.com",
        "message" to "Please quote 200 units of ACME-4711-BLK, delivered to Hamburg."
    ), // (optional)
    form_id = "", // (optional)
    form_slug = "contact", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    source = "/contact", // (optional)
    status = FormSubmissionStatus.NEW, // (optional)
)
```
