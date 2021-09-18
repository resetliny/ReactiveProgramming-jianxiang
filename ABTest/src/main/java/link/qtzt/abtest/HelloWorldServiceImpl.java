package link.qtzt.abtest;

import org.springframework.stereotype.Service;

/**
 * @author: yuanlin
 * @date: 2021-08-23 17:16:54
 * @description:
 */

@Service
public class HelloWorldServiceImpl {
    public String sayHello(String id){
        try {
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Hello world:"+id;
    }
}
