package br.com.helpdev.jynx.entrypoint.messaging;

import java.util.UUID;

public interface ProcessedImagePublisher {

    void publishProcessedImage(UUID uuid);

}
