package br.com.helpdev.jynx.dataprovider.messaging.properties;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigProperties(prefix = "rabbitmq.subscriber")
@Getter
@Setter
@ToString
public class SubscriberProperties {

    private Queue queue;
    private String routingKey;

    @Getter
    @Setter
    @ToString
    public static class Queue {
        private String name;
        private boolean durable = true;
    }
}
