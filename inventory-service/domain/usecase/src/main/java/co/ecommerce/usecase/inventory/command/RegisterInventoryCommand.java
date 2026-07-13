package co.ecommerce.usecase.inventory.command;

public record RegisterInventoryCommand(String sku, Integer quantity) {
}
