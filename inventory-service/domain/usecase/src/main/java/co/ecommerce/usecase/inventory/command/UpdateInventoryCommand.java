package co.ecommerce.usecase.inventory.command;

public record UpdateInventoryCommand(String sku, Integer quantity) {
}
