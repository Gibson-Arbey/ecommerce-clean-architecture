package co.ecommerce.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "outbox_events")
public class OutboxEventJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateId; // Guardaremos el orderNumber

    private String type;        // Identificador del evento (ORDER_PLACED)

    @Column(columnDefinition = "TEXT")
    private String payload;     // El objeto convertido a JSON String

    private LocalDateTime createdAt;

    private Boolean processed;  // Estado para el futuro proceso de envío
}
