```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PagesEditor

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pagesEditor = PagesEditor(client)

val result = pagesEditor.pagesEditorState(
    page_id = "", 
    langcode = "de", // (optional)
    index = 1, // (optional)
)
```
