package br.com.helpdev.jynx.dataprovider.publisher;

import br.com.helpdev.jynx.config.RabbitMQConnector;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorPublisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class RabbitMQLabelDetectorPublisher implements LabelDetectorPublisher {

    private final RabbitMQConnector rabbit;

    @Inject
    RabbitMQLabelDetectorPublisher(final RabbitMQConnector rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void notifyToProcess(final UUID uuid) {
        //TODO
    }

}
