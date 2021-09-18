package link.qtzt.abtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.kafka.receiver.ReceiverOptions;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * 这里与之前使用@KafkaListener注解实现的消息监听者不同，不过也非常简单，分为两个步骤：
 * （1）ReceiverOptions#subscription方法将ReceiverOptions关联到kafka主题
 * （2）创建ReactiveKafkaConsumerTemplate，并注册subscribe的回调函数消费消息。
 * 提示：receiveAutoAck方法会自动提交消费组offset。
 *
 * @author: yuanlin
 * @date: 2021-09-16 17:03:51
 * @description:
 */

@Service
@Slf4j
public class ReactiveKafkaConsumer {

    @Autowired
    private KafkaProperties properties;

    public static final String TEST_TOPIC = "test";
    @PostConstruct
    public void consumer() {
        ReceiverOptions<Long, String> options = ReceiverOptions.create(properties.getConsumer().buildProperties());
        options = options.subscription(Collections.singleton(TEST_TOPIC));
        new ReactiveKafkaConsumerTemplate(options)
                .receiveAutoAck()
                .subscribe(record -> {
                    log.info("TEST_TOPIC Record:" + record);
                });
    }

}
