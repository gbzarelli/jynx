package br.com.helpdev.jynx.config;

import br.com.helpdev.jynx.config.properties.PublisherProcessedProperties;
import br.com.helpdev.jynx.config.properties.PublisherToProcessProperties;
import br.com.helpdev.jynx.config.properties.SubscriberProperties;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@Startup
@ApplicationScoped
class JynxRabbitMQConfiguration {

    private final RabbitMQConnector connector;
    private final PublisherToProcessProperties publisherToProcessProperties;
    private final SubscriberProperties subscriberProperties;
    private final PublisherProcessedProperties publisherProcessedProperties;

    @Inject
    JynxRabbitMQConfiguration(final RabbitMQConnector connector,
                              final PublisherToProcessProperties publisherToProcessProperties,
                              final PublisherProcessedProperties publisherProcessedProperties,
                              final SubscriberProperties subscriberProperties) throws IOException {
        this.publisherProcessedProperties = publisherProcessedProperties;
        System.out.println(publisherToProcessProperties);
        System.out.println(subscriberProperties);
        this.connector = connector;
        this.publisherToProcessProperties = publisherToProcessProperties;
        this.subscriberProperties = subscriberProperties;
        init();
    }

    void init() throws IOException {
        final var channel = connector.getChannel();

        channel.exchangeDeclare(publisherToProcessProperties.getExchange().getName(),
                publisherToProcessProperties.getExchange().getType(),
                publisherToProcessProperties.getExchange().isDurable()
        );

        channel.exchangeDeclare(publisherProcessedProperties.getExchange().getName(),
                publisherProcessedProperties.getExchange().getType(),
                publisherProcessedProperties.getExchange().isDurable()
        );

        channel.queueDeclare(subscriberProperties.getQueue().getName(),
                subscriberProperties.getQueue().isDurable(),
                false, false, null);

        channel.queueBind(subscriberProperties.getQueue().getName(),
                publisherToProcessProperties.getExchange().getName(),
                subscriberProperties.getRoutingKey());
    }

}
