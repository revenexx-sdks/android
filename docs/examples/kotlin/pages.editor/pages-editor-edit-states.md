```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PagesEditor
import com.revenexx.enums.PageEditStateStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pagesEditor = PagesEditor(client)

val result = pagesEditor.pagesEditorEditStates(
    status = PageEditStateStatus.ACTIVE, // (optional)
    limit = 1, // (optional)
    offset = 1, // (optional)
)
```
