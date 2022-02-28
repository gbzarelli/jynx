package br.com.helpdev.jynx.core.usecase.impl;

import br.com.helpdev.jynx.core.interfaces.ProcessedImagePublisher;
import br.com.helpdev.jynx.core.usecase.LabelDetectUseCase;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
@Slf4j
class LabelDetectUseCaseImpl implements LabelDetectUseCase {

    private final ProcessedImagePublisher processedImagePublisher;

    @Inject
    public LabelDetectUseCaseImpl(ProcessedImagePublisher processedImagePublisher) {
        this.processedImagePublisher = processedImagePublisher;
    }

    @Override
    public void process(final UUID uuid) {
        log.info(String.format("Message %s processed", uuid.toString()));
        processedImagePublisher.notifyProcessedMessage(uuid);
    }

}
