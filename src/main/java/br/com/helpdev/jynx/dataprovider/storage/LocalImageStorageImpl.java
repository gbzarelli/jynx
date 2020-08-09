package br.com.helpdev.jynx.dataprovider.storage;

import br.com.helpdev.jynx.core.interfaces.ImageStorage;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.nio.file.Path;

@ApplicationScoped
class LocalImageStorageImpl implements ImageStorage {

    @Override
    public Path write(final InputStream image) {
        return null;
    }

}
