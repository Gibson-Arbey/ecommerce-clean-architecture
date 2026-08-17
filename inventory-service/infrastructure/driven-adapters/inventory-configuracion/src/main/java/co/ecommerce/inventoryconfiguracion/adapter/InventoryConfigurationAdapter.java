package co.ecommerce.inventoryconfiguracion.adapter;

import co.ecommerce.model.inventoryconfiguration.InventoryConfiguration;
import co.ecommerce.model.inventoryconfiguration.gateways.InventoryConfigurationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InventoryConfigurationAdapter implements InventoryConfigurationRepository {

    private final boolean allowBackorders;

    public InventoryConfigurationAdapter(
            @Value("${inventory.allow-backorders:false}")
            boolean allowBackorders) {
        this.allowBackorders = allowBackorders;
    }

    @Override
    public InventoryConfiguration findInventoryConfiguration() {
        return InventoryConfiguration.restore(allowBackorders);
    }
}
