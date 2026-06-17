```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Customers;
import com.revenexx.enums.ContactRole;
import com.revenexx.enums.ContactStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Customers customers = new Customers(client);

customers.customersContactsCreate(
    "", // email 
    "", // first_name (optional)
    false, // is_primary (optional)
    "", // last_name (optional)
    "", // locale (optional)
    "", // organization_id (optional)
    "", // phone (optional)
    ContactRole.BUYER, // role (optional)
    ContactStatus.INVITED, // status (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
