```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orderlists;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orderlists orderlists = new Orderlists(client);

orderlists.orderlistsItemsList(
    "", // list_id 
    "", // id (optional)
    "", // product_id (optional)
    "ACME-4711-BLK", // sku (optional)
    "Copy paper A4, 80 g/m², white", // name (optional)
    "https://cdn.example.com/catalog/acme-4711-blk.jpg", // image (optional)
    12, // quantity (optional)
    "piece", // unit (optional)
    3.49, // price (optional)
    19, // tax_rate (optional)
    "CC-100", // cost_center_id (optional)
    "{}", // position_texts (optional)
    "CUST-4711", // custom_sku (optional)
    "office-supplies", // category_slug (optional)
    "paper", // subcategory_slug (optional)
    0, // position (optional)
    "{}", // metadata (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    50, // limit (optional)
    0, // offset (optional)
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
