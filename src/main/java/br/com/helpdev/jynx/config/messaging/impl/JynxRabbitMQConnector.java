package br.com.helpdev.jynx.config.messaging.impl;

import br.com.helpdev.jynx.config.messaging.RabbitMQConnector;
import br.com.helpdev.jynx.config.properties.PublisherProcessedProperties;
import br.com.helpdev.jynx.config.properties.PublisherToProcessProperties;
import br.com.helpdev.jynx.config.properties.RabbitMQConfigProperties;
import br.com.helpdev.jynx.config.properties.SubscriberProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Startup
@ApplicationScoped
class JynxRabbitMQConnector implements RabbitMQConnector {

    private final Connection connection;
    private final Channel channel;

    private final PublisherToProcessProperties publisherToProcessProperties;
    private final SubscriberProperties subscriberProperties;
    private final PublisherProcessedProperties publisherProcessedProperties;

    @Inject
    JynxRabbitMQConnector(final RabbitMQConfigProperties config,
                          final PublisherToProcessProperties publisherToProcessProperties,
                          final PublisherProcessedProperties publisherProcessedProperties,
                          final SubscriberProperties subscriberProperties) throws IOException, TimeoutException {

        this.publisherProcessedProperties = publisherProcessedProperties;
        this.publisherToProcessProperties = publisherToProcessProperties;
        this.subscriberProperties = subscriberProperties;

        final var factory = new ConnectionFactory();
        factory.setHost(config.getHost());
        factory.setPort(config.getPort());
        factory.setVirtualHost(config.getVhost());
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        factory.setAutomaticRecoveryEnabled(true);
        connection = factory.newConnection();
        channel = connection.createChannel();

        initJynxConfigurations();
    }

    void initJynxConfigurations() throws IOException {
        final var channel = getChannel();

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

    protected void onStop(@Observes final ShutdownEvent ev) {
        try {
            channel.close();
            connection.close();
        } catch (final Exception ignored) {
        }
    }

    public Channel getChannel() {
        return channel;
    }

}
