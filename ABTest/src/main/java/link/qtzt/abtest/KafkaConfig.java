package link.qtzt.abtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

/**
 * @author: yuanlin
 * @date: 2021-09-16 22:00:57
 * @description:
 */

@Configuration
public class KafkaConfig {
    @Autowired
    private KafkaProperties properties;

    /**
     * 首先，我们需要创建一个ReactiveKafkaProducerTemplate实例。
     * （目前SpringBoot会自动创建KafkaTemplate实例，但不会创建ReactiveKafkaProducerTemplate实例）。
     * @return
     */
    @Bean
    public ReactiveKafkaProducerTemplate reactiveKafkaProducerTemplate() {
        SenderOptions options = SenderOptions.create(properties.getProducer().buildProperties());
        ReactiveKafkaProducerTemplate template = new ReactiveKafkaProducerTemplate(options);
        return template;
    }
}
