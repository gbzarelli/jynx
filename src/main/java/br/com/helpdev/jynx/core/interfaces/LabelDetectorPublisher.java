package br.com.helpdev.jynx.core.interfaces;

import java.util.UUID;

public interface LabelDetectorPublisher {
    void notifyToProcess(UUID uuid);
}
