package co.ecommerce.mq.listener;

import co.ecommerce.mq.event.OrderCancelledEvent;
import co.ecommerce.mq.event.OrderConfirmedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = "notification-queue")
public class OrderEventListener {

    private final JavaMailSender mailSender;

    @RabbitHandler
    public void handleOrderConfirmedEvent(OrderConfirmedEvent event) {
        log.info("Evento recibido en Notification para Orden: {}", event.orderNumber());
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
    }

    @RabbitHandler
    public void handleOrderCancelledEvent(OrderCancelledEvent event) {
        log.info("Evento recibido en Notification para Orden: {}", event.orderNumber());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("");
        mailMessage.setTo(event.email());
        mailMessage.setSubject("Orden cancelada - " + event.orderNumber());
        mailMessage.setText(String.format("""
                    ¡Hola!
                
                    Lamentamos informarte que tu pedido con número %s ha sido cancelado.
                
                    Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.
                
                    Gracias por confiar en nosotros.
                
                    Saludos,
                    Equipo Ecommerce
                """, event.orderNumber()));
        mailSender.send(mailMessage);
        log.info("Correo enviado exitosamente para la orden cancelada: {}", event.orderNumber());
    }
}
