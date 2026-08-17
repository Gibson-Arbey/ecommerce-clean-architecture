package co.ecommerce.mq.listener;

import co.ecommerce.mq.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final JavaMailSender mailSender;

    @RabbitListener(queues = "notification-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        log.info("Evento recibido en Notification para Orden: {}", event.orderNumber());
        event.items().forEach(item -> {
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom("");
                mailMessage.setTo(event.email());
                mailMessage.setSubject("Orden confirmada - " + event.orderNumber());
                mailMessage.setText(String.format("""
                    ¡Hola!
            
                    Tu pedido con número %s ha sido recibido exitosamente.
            
                    Pronto recibirás la guía de envío con los detalles de la entrega.
            
                    Gracias por confiar en nosotros.
            
                    Saludos,
                    Equipo Ecommerce
                """, event.orderNumber()));
                mailSender.send(mailMessage);
                log.info("Correo enviado exitosamente para la orden: {}", event.orderNumber());
            } catch (Exception e) {
                log.error("Error enviando correo: {}", e.getMessage());
            }
        });
    }
}
