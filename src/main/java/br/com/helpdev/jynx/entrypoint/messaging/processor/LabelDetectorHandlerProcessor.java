package br.com.helpdev.jynx.entrypoint.messaging.processor;

import br.com.helpdev.jynx.core.usecase.LabelDetectUseCase;
import br.com.helpdev.jynx.entrypoint.messaging.ConsumerListener;
import br.com.helpdev.jynx.entrypoint.messaging.LabelDetectorConsumer;
import br.com.helpdev.jynx.entrypoint.messaging.ProcessedImagePublisher;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

import static java.lang.String.format;

@ApplicationScoped
@Slf4j
public class LabelDetectorHandlerProcessor implements ConsumerListener {

    private final LabelDetectUseCase labelDetectUseCase;
    private final ProcessedImagePublisher processedImagePublisher;

    @Inject
    LabelDetectorHandlerProcessor(final LabelDetectUseCase labelDetectUseCase,
                                  final ProcessedImagePublisher processedImagePublisher,
                                  final LabelDetectorConsumer labelDetectorConsumer) {
        this.labelDetectUseCase = labelDetectUseCase;
        this.processedImagePublisher = processedImagePublisher;
        labelDetectorConsumer.register(this);
    }

    @Override
    public void handleDelivery(final Envelope envelope,
                               final byte[] body) {
        final var uuid = UUID.fromString(new String(body));
        try {
            labelDetectUseCase.process(uuid);
            notifyProcessedMessage(uuid);
        } catch (final Exception exception) {
            log.error(format("Failure in consume message - %s", uuid.toString()),
                    exception);
            throw exception;
        }
    }


    private void notifyProcessedMessage(final UUID uuid) {
        try {
            processedImagePublisher.publishProcessedImage(uuid);
        } catch (final Exception exception) {
            log.error(format("Failure in notify processed message - %s", uuid.toString()),
                    exception);
        }
    }

}
