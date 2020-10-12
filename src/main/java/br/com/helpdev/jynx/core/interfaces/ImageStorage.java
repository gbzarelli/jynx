package br.com.helpdev.jynx.core.interfaces;

import br.com.helpdev.jynx.core.entity.SavedImage;

import java.io.IOException;
import java.io.InputStream;

public interface ImageStorage {
    SavedImage write(InputStream image, String fileName) throws IOException;
}
