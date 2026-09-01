```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Forms
import com.revenexx.enums.FormSubmissionStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val forms = Forms(client)

val result = forms.formsSubmissionsList(
    id = "", // (optional)
    form_id = "", // (optional)
    form_slug = "contact", // (optional)
    source = "/contact", // (optional)
    status = FormSubmissionStatus.NEW, // (optional)
    created_at = "2026-01-31T09:15:00Z", // (optional)
    updated_at = "2026-01-31T09:15:00Z", // (optional)
    limit = 50, // (optional)
    offset = 0, // (optional)
    order = "created_at.desc", // (optional)
)
```
