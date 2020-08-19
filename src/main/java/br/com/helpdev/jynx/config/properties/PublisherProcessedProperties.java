package br.com.helpdev.jynx.config.properties;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigProperties(prefix = "rabbitmq.publisher.processed")
@Getter
@Setter
@ToString
public class PublisherProcessedProperties {

    private Exchange exchange;
    private String routingKey;

    @Getter
    @Setter
    @ToString
    public static class Exchange {
        private String name;
        private String type;
        private boolean durable = true;
    }

}
