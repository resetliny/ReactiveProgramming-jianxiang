package link.qtzt.abtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yuanlin
 * @date: 2021-09-16 15:04:08
 * @description:
 */

@RestController
@RequestMapping("/async")
public class AsyncController {

    public static AtomicLong REQUEST_TOTAL = new AtomicLong(0);

    @GetMapping("/delay/{param}")
    public Mono<String> hello(@PathVariable long param) {
        return Mono.just("this is async web return")
                .doOnNext(s-> System.out.println("async NO "+REQUEST_TOTAL.incrementAndGet()))
                //这里同样阻塞xx秒
                .delayElement(Duration.ofSeconds(param));
    }
}