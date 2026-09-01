```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CartsIo
import com.revenexx.enums.CartIoApplyMode
import com.revenexx.enums.CartIoDirection
import com.revenexx.enums.CartIoEntity
import com.revenexx.enums.CartIoFormat

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val cartsIo = CartsIo(client)

val result = cartsIo.cartsIoProfilesUpdate(
    id = "", 
    apply_mode = CartIoApplyMode.INSERT, // (optional)
    direction = CartIoDirection.IMPORT, // (optional)
    entity = CartIoEntity.CARTS, // (optional)
    format = CartIoFormat.JSON, // (optional)
    is_template = true, // (optional)
    mapping = mapOf( "a" to "b" ), // (optional)
    name = "cart-export-csv", // (optional)
    options = mapOf( "a" to "b" ), // (optional)
)
```
