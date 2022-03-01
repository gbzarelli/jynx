package br.com.helpdev.jynx.dataprovider.apis;

import br.com.helpdev.jynx.core.entity.LabelDetectorInformation;
import br.com.helpdev.jynx.core.entity.RegisterImage;
import br.com.helpdev.jynx.core.interfaces.ImageLabelDetector;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
public class VisionLabelDetectorImpl implements ImageLabelDetector {

    @Override
    public LabelDetectorInformation detectLabels(RegisterImage registeredImage) {
        //TODO get registeredImage.path from bucket and use Vision API to detect labels
        return LabelDetectorInformation.builder().build();
    }

}
