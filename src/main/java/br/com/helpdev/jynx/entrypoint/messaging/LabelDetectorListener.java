package br.com.helpdev.jynx.entrypoint.messaging;

import br.com.helpdev.jynx.config.messaging.RabbitMQConnector;
import br.com.helpdev.jynx.config.properties.PublisherProcessedProperties;
import br.com.helpdev.jynx.config.properties.SubscriberProperties;
import br.com.helpdev.jynx.core.usecase.LabelDetectUseCase;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.UUID;

import static java.lang.String.format;

@ApplicationScoped
@Slf4j
public class LabelDetectorListener {

    public static final boolean AUTO_ACK = false;
    public static final String CONSUME_TAG = LabelDetectorListener.class.getSimpleName();

    private final LabelDetectUseCase labelDetectUseCase;
    private final SubscriberProperties subscriberProperties;
    private final PublisherProcessedProperties publisherProcessedProperties;
    private final DefaultConsumer defaultConsumer;

    @Inject
    LabelDetectorListener(final LabelDetectUseCase labelDetectUseCase,
                          final RabbitMQConnector rabbitMQConnector,
                          final SubscriberProperties subscriberProperties,
                          final PublisherProcessedProperties publisherProcessedProperties) {
        this.labelDetectUseCase = labelDetectUseCase;
        this.subscriberProperties = subscriberProperties;
        this.publisherProcessedProperties = publisherProcessedProperties;
        this.defaultConsumer = new DefaultConsumer(rabbitMQConnector.getChannel()) {
            @Override
            public void handleDelivery(final String consumerTag,
                                       final Envelope envelope,
                                       final AMQP.BasicProperties properties,
                                       final byte[] body) throws IOException {
                consumeQueue(envelope, body);
            }
        };
    }

    void onStart(@Observes final StartupEvent ev) throws IOException {
        getChannel().basicConsume(subscriberProperties.getQueue().getName(),
                AUTO_ACK, CONSUME_TAG, defaultConsumer);
    }

    private void consumeQueue(final Envelope envelope,
                              final byte[] body) throws IOException {
        final var uuid = UUID.fromString(new String(body));
        processMessage(uuid);
        notifyProcessedMessage(uuid);
        ackMessage(envelope);
    }


    private void processMessage(final UUID uuid) {
        try {
            labelDetectUseCase.process(uuid);
        } catch (final Throwable throwable) {
            log.error(format("Failure in consume message - %s", uuid.toString()),
                    throwable);
            throw throwable;
        }
    }

    private void notifyProcessedMessage(final UUID uuid) {
        try {
            publishProcessedImage(uuid);
        } catch (final Exception exception) {
            log.error(format("Failure in notify processed message - %s", uuid.toString()),
                    exception);
        }
    }

    private void publishProcessedImage(final UUID uuid) throws IOException {
        getChannel().basicPublish(publisherProcessedProperties.getExchange().getName(),
                publisherProcessedProperties.getRoutingKey(), null, uuid.toString().getBytes());
    }


    private void ackMessage(final Envelope envelope) throws IOException {
        getChannel().basicAck(envelope.getDeliveryTag(), false);
    }

    Channel getChannel() {
        return defaultConsumer.getChannel();
    }

}
