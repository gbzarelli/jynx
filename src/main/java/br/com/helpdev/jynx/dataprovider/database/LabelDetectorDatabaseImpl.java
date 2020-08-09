package br.com.helpdev.jynx.dataprovider.database;

import br.com.helpdev.jynx.core.entity.LabelDetectorInformation;
import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.entity.Status;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorDatabase;

import java.util.UUID;

class LabelDetectorDatabaseImpl implements LabelDetectorDatabase {

    @Override
    public UUID registerImage(final RegisterImage registerImage) {
        return null;
    }

    @Override
    public void registerInformation(final UUID uuid,
                                    final LabelDetectorInformation information) {

    }

    @Override
    public LabelDetectorInformation getInformation(final UUID uuid) {
        return null;
    }

    @Override
    public void updateStatus(final UUID uuid,
                             final Status status) {

    }
}
