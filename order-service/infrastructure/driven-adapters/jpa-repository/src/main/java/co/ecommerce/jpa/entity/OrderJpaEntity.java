package co.ecommerce.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private String userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval es clave para Updates
    @JoinColumn(name = "order_id") // Crea la FK en la tabla de items
    private List<OrderItemJpaEntity> items;
}
