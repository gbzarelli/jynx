package br.com.helpdev.jynx.dataprovider.messaging;

import br.com.helpdev.jynx.dataprovider.messaging.properties.SubscriberProperties;
import br.com.helpdev.jynx.entrypoint.messaging.ConsumerListener;
import br.com.helpdev.jynx.entrypoint.messaging.LabelDetectorConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
@Slf4j
public class RabbitMQLabelDetectorConsumerImpl implements LabelDetectorConsumer {

    public static final boolean AUTO_ACK = false;
    public static final String CONSUME_TAG = RabbitMQLabelDetectorConsumerImpl.class.getSimpleName();

    private final RabbitMQConnector rabbitMQConnector;
    private final SubscriberProperties subscriberProperties;

    @Inject
    RabbitMQLabelDetectorConsumerImpl(final RabbitMQConnector rabbitMQConnector,
                                      final SubscriberProperties subscriberProperties) {
        this.rabbitMQConnector = rabbitMQConnector;
        this.subscriberProperties = subscriberProperties;

    }

    @Override
    @SneakyThrows
    public void register(final ConsumerListener consumer) {
        final var defaultConsumer = new DefaultConsumer(rabbitMQConnector.getChannel()) {
            @Override
            public void handleDelivery(final String consumerTag,
                                       final Envelope envelope,
                                       final AMQP.BasicProperties properties,
                                       final byte[] body) throws IOException {
                consumer.handleDelivery(body);
                getChannel().basicAck(envelope.getDeliveryTag(), false);
            }
        };
        defaultConsumer.getChannel().basicConsume(subscriberProperties.getQueue().getName(),
                AUTO_ACK, CONSUME_TAG, defaultConsumer);
    }

}
