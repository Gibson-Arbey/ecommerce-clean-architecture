package co.ecommerce.model.inventoryconfiguration;
import lombok.Getter;
//import lombok.NoArgsConstructor;


@Getter
public class InventoryConfiguration {

    private final Boolean allowBackorders;

    private InventoryConfiguration(Boolean allowBackorders) {
        this.allowBackorders = allowBackorders;
    }

    public static InventoryConfiguration restore(Boolean allowBackorders) {
        return new InventoryConfiguration(allowBackorders);
    }
}
