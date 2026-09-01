```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CartsIo;
import com.revenexx.enums.CartIoDirection;
import com.revenexx.enums.CartIoEntity;
import com.revenexx.enums.CartIoFormat;
import com.revenexx.enums.CartIoApplyMode;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CartsIo cartsIo = new CartsIo(client);

cartsIo.cartsIoProfilesList(
    "", // id (optional)
    "cart-export-csv", // name (optional)
    CartIoDirection.IMPORT, // direction (optional)
    CartIoEntity.CARTS, // entity (optional)
    CartIoFormat.JSON, // format (optional)
    CartIoApplyMode.INSERT, // apply_mode (optional)
    true, // is_template (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
