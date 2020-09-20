package br.com.helpdev.jynx.dataprovider.messaging;

import br.com.helpdev.jynx.dataprovider.messaging.properties.PublisherProcessedProperties;
import br.com.helpdev.jynx.entrypoint.messaging.ProcessedImagePublisher;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class RabbitMQProcessedImagePublisherImpl implements ProcessedImagePublisher {

    private final RabbitMQConnector rabbit;
    private final PublisherProcessedProperties properties;

    @Inject
    RabbitMQProcessedImagePublisherImpl(final RabbitMQConnector rabbit,
                                        final PublisherProcessedProperties properties) {
        this.rabbit = rabbit;
        this.properties = properties;
    }

    @Override
    public void publishProcessedImage(UUID uuid) {
        try {
            rabbit.getChannel().basicPublish(properties.getExchange().getName(),
                    properties.getRoutingKey(), null, uuid.toString().getBytes());
        } catch (final Exception exception) {
            log.error(String.format("Failure in notify processed message - %s", uuid.toString()),
                    exception);
        }
    }

}
