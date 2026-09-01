```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PagesCollaboration

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pagesCollaboration = PagesCollaboration(client)

val result = pagesCollaboration.pagesEditorCommentsUnresolve(
    page_id = "", 
    uuid = "", 
)
```
