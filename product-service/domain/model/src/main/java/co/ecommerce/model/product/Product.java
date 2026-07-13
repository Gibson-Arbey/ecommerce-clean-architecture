package co.ecommerce.model.product;

import co.ecommerce.model.exception.InvalidFieldException;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {

    private final String id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    private Product(String id, String name, String description, BigDecimal price) {

        if(name == null) throw new InvalidFieldException("name is null");
        if(price == null) throw new InvalidFieldException("price is null");
        if(price.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidFieldException("price is less than or equal to zero");

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product create(String name, String description, BigDecimal price) {
        return new Product(null, name, description, price);
    }

    public static Product restore(String id, String name, String description, BigDecimal price) {
        return new Product(id, name, description, price);
    }
}
