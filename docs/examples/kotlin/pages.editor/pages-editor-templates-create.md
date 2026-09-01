```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PagesEditor

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pagesEditor = PagesEditor(client)

val result = pagesEditor.pagesEditorTemplatesCreate(
    page_id = "", 
    label = "Hero with two teasers", 
    uuids = listOf(), 
    description = "Full-width hero followed by a two-column teaser row.", // (optional)
    fieldName = "content", // (optional)
    isDefault = true, // (optional)
    pageBundle = "standard", // (optional)
)
```
