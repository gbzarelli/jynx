package br.com.helpdev.jynx.dataprovider.messaging;

import br.com.helpdev.jynx.core.interfaces.LabelDetectorPublisher;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.MultiEmitterProcessor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class RabbitMQLabelDetectorPublisherImpl implements LabelDetectorPublisher {

    //Publish sample using @Outgoing asynchronous
    private final MultiEmitterProcessor<String> emitter = MultiEmitterProcessor.create();

    @Override
    public void notifyToProcess(final UUID uuid) {
        try {
            emitter.emit(uuid.toString());
        } catch (final Exception exception) {
            log.error(String.format("Failure in notify to process message - %s", uuid.toString()),
                    exception);
        }
    }

    @Outgoing("jynx-to-process-image-exchange")
    public Multi<String> outgoing() {
        return Multi.createFrom().publisher(emitter);
    }

}
