package br.com.helpdev.jynx.dataprovider.messaging;

import br.com.helpdev.jynx.core.interfaces.ProcessedImagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class RabbitMQProcessedImagePublisherImpl implements ProcessedImagePublisher {

    //Publish sample using @Channel synchronous
    @Channel("image-processed-emitter")
    Emitter<String> imageProcessedEmitter;

    @Override
    public void notifyProcessedMessage(UUID uuid) {
        try {
            imageProcessedEmitter.send(uuid.toString());
        } catch (final Exception exception) {
            log.error(String.format("Failure in notify processed message - %s", uuid.toString()),
                    exception);
        }
    }

}
