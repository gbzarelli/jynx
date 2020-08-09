package br.com.helpdev.jynx.dataprovider.storage;

import br.com.helpdev.jynx.core.interfaces.ImageStorage;

import java.io.InputStream;
import java.nio.file.Path;

class LocalImageStorageImpl implements ImageStorage {

    @Override
    public Path write(InputStream image) {
        return null;
    }

}
