package br.com.helpdev.jynx.dataprovider.database;

import br.com.helpdev.jynx.core.entity.LabelDetectorInformation;
import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.entity.SavedImage;
import br.com.helpdev.jynx.core.entity.Status;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorDatabase;
import br.com.helpdev.jynx.dataprovider.database.entity.ImageEntity;
import br.com.helpdev.jynx.dataprovider.database.entity.LabelDetectorEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
class LabelDetectorDatabaseImpl implements LabelDetectorDatabase {

    @Override
    @Transactional
    public UUID registerImage(final RegisterImage registerImage) {
        final var labelDetectorEntity = LabelDetectorEntity.builder()
                .imageEntity(ImageEntity.builder()
                        .bucketId(registerImage.getSavedImage().getId())
                        .imageName(registerImage.getImageName())
                        .path(registerImage.getSavedImage().getPath())
                        .location(registerImage.getSavedImage().getLocation())
                        .build())
                .status(registerImage.getStatus())
                .build();
        labelDetectorEntity.getImageEntity().persistAndFlush();
        labelDetectorEntity.persistAndFlush();
        return labelDetectorEntity.getUuid();
    }

    @Override
    @Transactional
    public void updateStatus(final UUID uuid,
                             final Status status) {
        LabelDetectorEntity.<LabelDetectorEntity>findByIdOptional(uuid)
                .orElseThrow()
                .updateStatus(status);
    }

    @Override
    public RegisterImage getRegisteredImage(final UUID uuid) {
        final var byId = LabelDetectorEntity.<LabelDetectorEntity>findByIdOptional(uuid)
                .orElseThrow();
        return RegisterImage.builder()
                .savedImage(SavedImage.builder()
                        .path(byId.getImageEntity().getPath())
                        .location(byId.getImageEntity().getLocation())
                        .id(byId.getImageEntity().getBucketId())
                        .build())
                .imageName(byId.getImageEntity().getImageName())
                .status(byId.getStatus())
                .uuid(uuid)
                .build();
    }

    @Override
    public void registerInformation(final UUID uuid,
                                    final LabelDetectorInformation information) {
        //TODO
    }

    @Override
    public LabelDetectorInformation getInformation(final UUID uuid) {
        //TODO
        return null;
    }


}
