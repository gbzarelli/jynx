package br.com.helpdev.jynx.dataprovider.messaging;

import br.com.helpdev.jynx.core.interfaces.ProcessedImagePublisher;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class RabbitMQProcessedImagePublisherImpl implements ProcessedImagePublisher {

    @Override
    public void notifyProcessedMessage(UUID uuid) {
//        try {
//            rabbit.getChannel().basicPublish(properties.getExchange().getName(),
//                    properties.getRoutingKey(), null, uuid.toString().getBytes());
//        } catch (final Exception exception) {
//            log.error(String.format("Failure in notify processed message - %s", uuid.toString()),
//                    exception);
//        }
    }

}
