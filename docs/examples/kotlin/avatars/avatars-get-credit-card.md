```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Avatars
import com.revenexx.enums.AvatarsGetCreditCardCode

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val avatars = Avatars(client)

val result = avatars.avatarsGetCreditCard(
    code = AvatarsGetCreditCardCode.AMEX,
    width = 1, // (optional)
    height = 1, // (optional)
    quality = 1, // (optional)
)
```
