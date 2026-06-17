```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Carts;
import com.revenexx.enums.CartIoApplyMode;
import com.revenexx.enums.CartIoDirection;
import com.revenexx.enums.CartIoEntity;
import com.revenexx.enums.CartIoFormat;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Carts carts = new Carts(client);

carts.cartsIoProfilesUpdate(
    "", // id 
    CartIoApplyMode.INSERT, // apply_mode (optional)
    CartIoDirection.IMPORT, // direction (optional)
    CartIoEntity.CARTS, // entity (optional)
    CartIoFormat.JSON, // format (optional)
    false, // is_template (optional)
    Map.of("a", "b"), // mapping (optional)
    "", // name (optional)
    Map.of("a", "b"), // options (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
