package br.com.helpdev.jynx.entrypoint.messaging;

import br.com.helpdev.jynx.core.usecase.LabelDetectUseCase;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@ApplicationScoped
public class LabelDetectorQueueListener {

    private final LabelDetectUseCase labelDetectUseCase;

    @Inject
    public LabelDetectorQueueListener(final LabelDetectUseCase labelDetectUseCase) {
        this.labelDetectUseCase = labelDetectUseCase;
    }

    public void handleDelivery(final byte[] payload) {
        final var uuid = UUID.fromString(new String(payload));
        try {
            labelDetectUseCase.process(uuid);
        } catch (final Exception exception) {
            log.error(format("Failure in consume message - %s", uuid.toString()),
                    exception);
            throw exception;
        }
    }

}
