```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;
import com.revenexx.enums.OrderStatus;
import com.revenexx.enums.OrderPaymentStatus;
import com.revenexx.enums.OrderFulfillmentStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersList(
    "", // id (optional)
    "ORD-000123", // number (optional)
    "PO-2026-0042", // customer_order_number (optional)
    "ERP-4711", // external_ref (optional)
    "2026-01-01T12:00:00Z", // acknowledged_at (optional)
    "", // cart_id (optional)
    "", // contact_id (optional)
    "", // organization_id (optional)
    "", // channel_id (optional)
    "EUR", // currency (optional)
    OrderStatus.PENDING, // status (optional)
    OrderPaymentStatus.OPEN, // payment_status (optional)
    OrderFulfillmentStatus.UNFULFILLED, // fulfillment_status (optional)
    true, // on_hold (optional)
    "Credit check pending", // hold_reason (optional)
    3, // item_count (optional)
    149.7, // subtotal (optional)
    5.9, // shipping_total (optional)
    29.56, // tax_total (optional)
    185.16, // grand_total (optional)
    "2026-01-01T12:00:00Z", // placed_at (optional)
    "2026-01-01T12:00:00Z", // completed_at (optional)
    "2026-01-01T12:00:00Z", // cancelled_at (optional)
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
