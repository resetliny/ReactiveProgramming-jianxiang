package link.qtzt.abtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;

/**
 * @author: yuanlin
 * @date: 2021-08-23 17:18:39
 * @description:
 */

@RestController
public class TestController {
    @Autowired
    HelloWorldServiceImpl helloWorldService;

    @Resource(name = "scheduler")
    Scheduler scheduler;

    @GetMapping("/api/h1")
    public Mono<String> h1(String id){
        return Mono.just("Hello WebFlux")
                .publishOn(scheduler)
                .map(s -> helloWorldService.sayHello(id));
    }

    @GetMapping("/api/h2")
    public Mono<String> h2(String id){
        return Mono.just("Hello WebFlux")
                .publishOn(Schedulers.elastic())
                .map(s -> helloWorldService.sayHello(id));
    }

    @GetMapping("/api/h3")
    public String h3(String id){
        return helloWorldService.sayHello(id);
    }

}
