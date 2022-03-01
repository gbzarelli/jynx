package br.com.helpdev.jynx.core.usecase.impl;

import br.com.helpdev.jynx.core.entity.Status;
import br.com.helpdev.jynx.core.interfaces.ImageLabelDetector;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorDatabase;
import br.com.helpdev.jynx.core.interfaces.ProcessedImagePublisher;
import br.com.helpdev.jynx.core.usecase.LabelDetectUseCase;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
@Slf4j
class LabelDetectUseCaseImpl implements LabelDetectUseCase {

    private final ImageLabelDetector imageLabelDetector;
    private final ProcessedImagePublisher processedImagePublisher;
    private final LabelDetectorDatabase database;

    @Inject
    public LabelDetectUseCaseImpl(ImageLabelDetector imageLabelDetector,
                                  ProcessedImagePublisher processedImagePublisher,
                                  LabelDetectorDatabase database) {
        this.imageLabelDetector = imageLabelDetector;
        this.processedImagePublisher = processedImagePublisher;
        this.database = database;
    }

    @Override
    public void process(final UUID uuid) {
        final var registeredImage = database.getRegisteredImage(uuid);
        final var labelDetectorInformation = imageLabelDetector.detectLabels(registeredImage);
        database.registerInformation(uuid, labelDetectorInformation);
        database.updateStatus(uuid, Status.SUCCESS);
        log.info(String.format("Message %s processed", uuid.toString()));
        processedImagePublisher.notifyProcessedMessage(uuid);
    }

}
