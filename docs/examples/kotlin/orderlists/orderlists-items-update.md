```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orderlists

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orderlists = Orderlists(client)

val result = orderlists.orderlistsItemsUpdate(
    list_id = "", 
    id = "", 
    category_slug = "office-supplies", // (optional)
    cost_center_id = "CC-100", // (optional)
    custom_sku = "CUST-4711", // (optional)
    image = "https://cdn.example.com/catalog/acme-4711-blk.jpg", // (optional)
    metadata = mapOf(
        "erp_line_ref" to "4711-01"
    ), // (optional)
    name = "Copy paper A4, 80 g/m², white", // (optional)
    position = 0, // (optional)
    position_texts = listOf("Deliver to bay 3", "Engraving: Team A"), // (optional)
    price = 3.49, // (optional)
    product_id = "", // (optional)
    quantity = 12, // (optional)
    sku = "ACME-4711-BLK", // (optional)
    subcategory_slug = "paper", // (optional)
    tax_rate = 19, // (optional)
    unit = "piece", // (optional)
)
```
