package co.ecommerce.usecase.inventory.query;

public record SearchInventoriesQuery(String sku, Integer minQuantity, Integer maxQuantity) {

    public SearchInventoriesQuery {
        if (sku == null) {
            sku = "sku";
        }

        if (minQuantity == null) {
            minQuantity = 1;
        }

        if (maxQuantity == null) {
            maxQuantity = Integer.MAX_VALUE;
        }
    }
}
