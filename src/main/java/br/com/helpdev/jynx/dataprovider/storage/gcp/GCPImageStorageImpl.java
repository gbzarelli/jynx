package br.com.helpdev.jynx.dataprovider.storage.gcp;

import br.com.helpdev.jynx.core.entity.SavedImage;
import br.com.helpdev.jynx.core.interfaces.ImageStorage;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static java.lang.String.format;

@ApplicationScoped
class GCPImageStorageImpl implements ImageStorage {

    private final Storage storage;
    private final JynxImageBucket imageBucket;

    @Inject
    GCPImageStorageImpl(final Storage storage, final JynxImageBucket imageBucket) {
        this.storage = storage;
        this.imageBucket = imageBucket;
    }

    @Override
    public SavedImage write(final InputStream image, final String fileName) throws IOException {
        final var imageId = UUID.randomUUID().toString()
                .concat("$").concat(fileName);

        storage.createFrom(BlobInfo.newBuilder(imageBucket.toString(),
                imageId).build(), image);

        return SavedImage.builder()
                .id(imageId)
                .location(imageBucket.toString())
                .path(format("gcp://%s/%s", imageBucket, imageId))
                .build();
    }
}
