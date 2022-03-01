package br.com.helpdev.jynx.core.interfaces;

import br.com.helpdev.jynx.core.entity.LabelDetectorInformation;
import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.entity.Status;

import java.util.UUID;

public interface LabelDetectorDatabase {

    UUID registerImage(RegisterImage registerImage);

    void registerInformation(UUID uuid, LabelDetectorInformation information);

    LabelDetectorInformation getInformation(UUID uuid);

    RegisterImage getRegisteredImage(UUID uuid);

    void updateStatus(UUID uuid, Status status);
}
