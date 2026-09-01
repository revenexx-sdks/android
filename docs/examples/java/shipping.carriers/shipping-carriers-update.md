```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingCarriers;
import com.revenexx.enums.ShippingCarrierStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingCarriers shippingCarriers = new ShippingCarriers(client);

shippingCarriers.shippingCarriersUpdate(
    "", // id 
    "acme-parcel", // code (optional)
    List.of("DE", "AT", "CH"), // countries (optional)
    "16:00", // cutoff_time (optional)
    1, // eta_days_max (optional)
    1, // eta_days_min (optional)
    1, // handling_days (optional)
    Map.of(
        "de", "Acme Paketdienst",
        "en", "Acme Parcel"
    ), // labels (optional)
    Map.of(
        "contract", "ACME-2026",
        "customer_number", "4711"
    ), // metadata (optional)
    "Acme Parcel", // name (optional)
    1, // position (optional)
    "express", // service_level (optional)
    ShippingCarrierStatus.ACTIVE, // status (optional)
    "https://track.example.com/parcels/{tracking_code}", // tracking_url_template (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
