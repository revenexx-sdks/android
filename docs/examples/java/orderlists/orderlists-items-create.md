```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orderlists;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orderlists orderlists = new Orderlists(client);

orderlists.orderlistsItemsCreate(
    "", // list_id 
    "Copy paper A4, 80 g/m², white", // name 
    "office-supplies", // category_slug (optional)
    "CC-100", // cost_center_id (optional)
    "CUST-4711", // custom_sku (optional)
    "https://cdn.example.com/catalog/acme-4711-blk.jpg", // image (optional)
    Map.of(
        "erp_line_ref", "4711-01"
    ), // metadata (optional)
    0, // position (optional)
    List.of("Deliver to bay 3", "Engraving: Team A"), // position_texts (optional)
    3.49, // price (optional)
    "", // product_id (optional)
    12, // quantity (optional)
    "ACME-4711-BLK", // sku (optional)
    "paper", // subcategory_slug (optional)
    19, // tax_rate (optional)
    "piece", // unit (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
