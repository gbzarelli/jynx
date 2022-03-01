package br.com.helpdev.jynx.core.interfaces;

import br.com.helpdev.jynx.core.entity.LabelDetectorInformation;
import br.com.helpdev.jynx.core.entity.RegisterImage;

public interface ImageLabelDetector {
    LabelDetectorInformation detectLabels(RegisterImage registeredImage);
}
