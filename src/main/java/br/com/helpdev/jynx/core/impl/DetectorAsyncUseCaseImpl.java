package br.com.helpdev.jynx.core.impl;

import br.com.helpdev.jynx.core.DetectorAsyncUseCase;
import br.com.helpdev.jynx.core.entity.LabelDetectorStatus;
import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.entity.Status;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorDatabase;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorPublisher;
import br.com.helpdev.jynx.core.interfaces.ImageStorage;

import javax.inject.Inject;
import java.io.InputStream;

class DetectorAsyncUseCaseImpl implements DetectorAsyncUseCase {

    private final ImageStorage imageStorage;
    private final LabelDetectorDatabase database;
    private final LabelDetectorPublisher publisher;

    @Inject
    DetectorAsyncUseCaseImpl(final ImageStorage imageStorage,
                             final LabelDetectorDatabase database,
                             final LabelDetectorPublisher publisher) {
        this.imageStorage = imageStorage;
        this.database = database;
        this.publisher = publisher;
    }

    @Override
    public LabelDetectorStatus registerImageToLabelDetect(final InputStream image,
                                                          final String fileName) {
        final var path = imageStorage.write(image);

        final var uuid = database.registerImage(RegisterImage.builder()
                .path(path)
                .imageName(fileName)
                .status(Status.PROCESSING)
                .build());

        try {
            publisher.notifyToProcess(uuid);
        } catch (final RuntimeException ex) {
            database.updateStatus(uuid, Status.FAILURE);
            return LabelDetectorStatus.builder()
                    .status(Status.FAILURE)
                    .message(ex.getMessage())
                    .build();
        }

        return LabelDetectorStatus.builder()
                .uuid(uuid)
                .status(Status.PROCESSING)
                .build();
    }
}
