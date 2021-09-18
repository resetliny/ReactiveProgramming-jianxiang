package link.qtzt.abtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

import java.time.LocalDateTime;

/**
 * @author: yuanlin
 * @date: 2021-09-16 22:02:40
 * @description:
 */

@RestController
@Slf4j
public class ReactiveKafkaProducer {
    @Autowired
    private ReactiveKafkaProducerTemplate template;

    public static final String TEST_TOPIC = "test";
    @RequestMapping("/kafka/reactive")
    public void add() {
        String msg = "我是一条响应消息:"+ LocalDateTime.now().getNano();
        template.send(TEST_TOPIC, JSON.toJSONString(msg, SerializerFeature.WriteNullStringAsEmpty));
    }

}
