```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Forms
import com.revenexx.enums.FormStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val forms = Forms(client)

val result = forms.formsCreate(
    name = "Price request", 
    slug = "price-request", 
    definition = listOf(mapOf(
        "$formkit" to "text",
        "label" to "Company",
        "name" to "company",
        "validation" to "required"
    ), mapOf(
        "$formkit" to "email",
        "label" to "Email",
        "name" to "email",
        "validation" to "required|email"
    ), mapOf(
        "$formkit" to "textarea",
        "label" to "What do you need a price for?",
        "name" to "message",
        "rows" to 4
    ), mapOf(
        "$el" to "p",
        "children" to "We answer price requests within one working day."
    )), // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    settings = mapOf( "a" to "b" ), // (optional)
    status = FormStatus.DRAFT, // (optional)
)
```
