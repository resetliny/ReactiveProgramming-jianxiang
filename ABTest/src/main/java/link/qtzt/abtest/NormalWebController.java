package link.qtzt.abtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yuanlin
 * @date: 2021-09-16 15:03:22
 * @description:
 */

@RestController
@RequestMapping("/normal")
public class NormalWebController {

    public static AtomicLong REQUEST_TOTAL = new AtomicLong(0);
    @GetMapping("/delay/{param}")
    public String delay(@PathVariable long param){
        System.out.println("normal NO "+REQUEST_TOTAL.incrementAndGet());
        try {
            //阻塞xx秒，模拟处理请求时间
            TimeUnit.SECONDS.sleep(param);
        } catch (InterruptedException e) {
            return "Error during thread sleep";
        }
        return "this is normal web return";
    }
}
