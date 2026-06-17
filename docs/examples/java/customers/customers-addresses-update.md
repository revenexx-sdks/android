```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Customers;
import com.revenexx.enums.AddressType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Customers customers = new Customers(client);

customers.customersAddressesUpdate(
    "", // id 
    "", // city (optional)
    "", // company (optional)
    "", // contact_id (optional)
    "", // country (optional)
    false, // is_default (optional)
    "", // name (optional)
    "", // organization_id (optional)
    "", // phone (optional)
    "", // region (optional)
    "", // street (optional)
    "", // street2 (optional)
    AddressType.BILLING, // type (optional)
    "", // zip (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
