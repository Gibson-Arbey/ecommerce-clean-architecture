package co.ecommerce.scheduler;

import co.ecommerce.usecase.outboxevent.ProcessOutboxEventsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRelayerScheduler {

    private final ProcessOutboxEventsUseCase processOutboxEventsUseCase;

    @Scheduled(fixedDelay = 10000)
    public void relayMessage() {
        processOutboxEventsUseCase.execute();
    }
}
