package br.com.helpdev.jynx.core.interfaces;

import java.io.InputStream;
import java.nio.file.Path;

public interface ImageStorage {
    Path write(InputStream image);
}
