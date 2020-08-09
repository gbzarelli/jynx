package br.com.helpdev.jynx.core;

import br.com.helpdev.jynx.core.entity.LabelDetectorStatus;

import java.io.InputStream;

public interface DetectorAsyncUseCase {
    LabelDetectorStatus registerImageToLabelDetect(InputStream image, String fileName);
}
