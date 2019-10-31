package chenqian.site.rabbitmqboottest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能简介:.
 *
 * @author cq
 * @version 1.0
 * *
 */
@RestController
@RequestMapping("/producer2")
public class Producer2Controller {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend("direct_exchange", "", msg);
    }
}
