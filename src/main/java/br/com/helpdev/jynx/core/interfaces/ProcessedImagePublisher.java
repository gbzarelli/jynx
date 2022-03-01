package br.com.helpdev.jynx.core.interfaces;

import java.util.UUID;

public interface ProcessedImagePublisher {
    void notifyProcessedMessage(UUID uuid);
}
