```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CartsIo
import com.revenexx.enums.CartIoDirection
import com.revenexx.enums.CartIoEntity
import com.revenexx.enums.CartIoFormat
import com.revenexx.enums.CartIoApplyMode

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val cartsIo = CartsIo(client)

val result = cartsIo.cartsIoProfilesList(
    id = "", // (optional)
    name = "cart-export-csv", // (optional)
    direction = CartIoDirection.IMPORT, // (optional)
    entity = CartIoEntity.CARTS, // (optional)
    format = CartIoFormat.JSON, // (optional)
    apply_mode = CartIoApplyMode.INSERT, // (optional)
    is_template = true, // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
)
```
