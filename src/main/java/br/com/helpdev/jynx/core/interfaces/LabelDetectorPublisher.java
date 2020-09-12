package br.com.helpdev.jynx.core.interfaces;

import br.com.helpdev.jynx.core.exception.FailureToPublishException;

import java.util.UUID;

public interface LabelDetectorPublisher {
    void notifyToProcess(UUID uuid) throws FailureToPublishException;
}
