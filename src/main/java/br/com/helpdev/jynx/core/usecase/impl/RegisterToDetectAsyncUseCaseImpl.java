package br.com.helpdev.jynx.core.usecase.impl;

import br.com.helpdev.jynx.core.entity.LabelDetectorStatus;
import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.entity.Status;
import br.com.helpdev.jynx.core.exception.FailureToPublishException;
import br.com.helpdev.jynx.core.interfaces.ImageStorage;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorDatabase;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorPublisher;
import br.com.helpdev.jynx.core.usecase.RegisterToDetectAsyncUseCase;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;

@ApplicationScoped
class RegisterToDetectAsyncUseCaseImpl implements RegisterToDetectAsyncUseCase {

    private final ImageStorage imageStorage;
    private final LabelDetectorDatabase database;
    private final LabelDetectorPublisher publisher;

    @Inject
    RegisterToDetectAsyncUseCaseImpl(final ImageStorage imageStorage,
                                     final LabelDetectorDatabase database,
                                     final LabelDetectorPublisher publisher) {
        this.imageStorage = imageStorage;
        this.database = database;
        this.publisher = publisher;
    }

    @SneakyThrows
    @Override
    public LabelDetectorStatus registerImageToLabelDetect(final InputStream image,
                                                          final String fileName) {
        final var path = imageStorage.write(image, fileName);

        final var uuid = database.registerImage(RegisterImage.builder()
                .savedImage(path)
                .imageName(fileName)
                .status(Status.PROCESSING)
                .build());

        try {
            publisher.notifyToProcess(uuid);
        } catch (final FailureToPublishException ex) {
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
