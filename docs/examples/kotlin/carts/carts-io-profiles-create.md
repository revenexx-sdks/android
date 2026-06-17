```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts
import com.revenexx.enums.CartIoDirection
import com.revenexx.enums.CartIoApplyMode
import com.revenexx.enums.CartIoEntity
import com.revenexx.enums.CartIoFormat

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsIoProfilesCreate(
    direction = CartIoDirection.IMPORT,
    name = "", 
    apply_mode = CartIoApplyMode.INSERT, // (optional)
    entity = CartIoEntity.CARTS, // (optional)
    format = CartIoFormat.JSON, // (optional)
    is_template = false, // (optional)
    mapping = mapOf( "a" to "b" ), // (optional)
    options = mapOf( "a" to "b" ), // (optional)
)
```
