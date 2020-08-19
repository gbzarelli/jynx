package br.com.helpdev.jynx.core.impl;

import br.com.helpdev.jynx.core.LabelDetectUseCase;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@Slf4j
class LabelDetectUseCaseImpl implements LabelDetectUseCase {

    @Override
    public void process(final UUID uuid) {
        log.info(String.format("Message %s processed", uuid.toString()));
    }

}
