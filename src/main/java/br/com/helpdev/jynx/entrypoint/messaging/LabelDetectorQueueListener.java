package br.com.helpdev.jynx.entrypoint.messaging;

import br.com.helpdev.jynx.core.usecase.LabelDetectUseCase;
import io.smallrye.common.annotation.Blocking;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.lang.String.format;

@Slf4j
@ApplicationScoped
public class LabelDetectorQueueListener {

    private final LabelDetectUseCase labelDetectUseCase;

    @Inject
    public LabelDetectorQueueListener(final LabelDetectUseCase labelDetectUseCase) {
        this.labelDetectUseCase = labelDetectUseCase;
    }

    @Incoming("label-detect")
    //@Blocking Indicate to run in another thread (solve the problem with worker in same project: https://groups.google.com/g/quarkus-dev/c/l_YcN3hv7b0?pli=1 )
    @Blocking
    public CompletionStage<Void> incoming(Message<String> message) {
        final var uuid = UUID.fromString(message.getPayload());
        try {
            labelDetectUseCase.process(uuid);
        } catch (final Exception exception) {
            log.error(format("Failure in consume message - %s", uuid), exception);
            return message.nack(exception);
        }
        return message.ack();
    }

}
