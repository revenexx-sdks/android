```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Forms
import com.revenexx.enums.FormsSubmissionsPruneStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val forms = Forms(client)

val result = forms.formsSubmissionsPrune(
    dry_run = true, // (optional)
    form_slug = "contact", // (optional)
    older_than_days = 1, // (optional)
    status = Forms.submissions.pruneStatus.NEW, // (optional)
)
```
