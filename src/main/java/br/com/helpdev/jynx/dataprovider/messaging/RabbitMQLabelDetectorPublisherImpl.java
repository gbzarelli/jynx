package br.com.helpdev.jynx.dataprovider.messaging;

import br.com.helpdev.jynx.dataprovider.messaging.properties.PublisherToProcessProperties;
import br.com.helpdev.jynx.core.exception.FailureToPublishException;
import br.com.helpdev.jynx.core.interfaces.LabelDetectorPublisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.UUID;

import static java.text.MessageFormat.format;

@ApplicationScoped
public class RabbitMQLabelDetectorPublisherImpl implements LabelDetectorPublisher {

    private final RabbitMQConnector rabbit;
    private final PublisherToProcessProperties properties;

    @Inject
    RabbitMQLabelDetectorPublisherImpl(final RabbitMQConnector rabbit,
                                       final PublisherToProcessProperties properties) {
        this.rabbit = rabbit;
        this.properties = properties;
    }

    @Override
    public void notifyToProcess(final UUID uuid) throws FailureToPublishException {
        try {
            rabbit.getChannel().basicPublish(properties.getExchangeName(),
                    properties.getRoutingKey(), null, uuid.toString().getBytes());
        } catch (IOException e) {
            throw new FailureToPublishException(
                    format("Failed to publish UUID {0} in exchange {1}", uuid,
                            properties.getExchangeName()), e);
        }
    }

}
