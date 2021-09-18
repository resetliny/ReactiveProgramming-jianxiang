package link.qtzt.abtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author: yuanlin
 * @date: 2021-09-16 17:03:51
 * @description:
 */

@RestController
public class SpringKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/kafka/normal")
    public void SpringKafkaSend(){
        String msg = "我是一条普通消息:"+ LocalDateTime.now().getNano();
        kafkaTemplate.sendDefault(JSON.toJSONString(msg, SerializerFeature.WriteNullStringAsEmpty));
    }
}
