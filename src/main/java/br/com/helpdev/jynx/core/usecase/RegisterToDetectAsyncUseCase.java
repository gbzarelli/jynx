package br.com.helpdev.jynx.core.usecase;

import br.com.helpdev.jynx.core.entity.LabelDetectorStatus;

import java.io.InputStream;

public interface RegisterToDetectAsyncUseCase {
    LabelDetectorStatus registerImageToLabelDetect(InputStream image, String fileName);
}
