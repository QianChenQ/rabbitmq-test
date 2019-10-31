package chenqian.site.rabbitmqboottest;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 功能简介:.
 *
 * @author cq
 * @version 1.0
 * *
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend("direct_exchange", "ssss", msg, new CorrelationData("123"));
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("<--------------start------------->");
            System.out.println(correlationData);
            System.out.println(ack);
            System.out.println(cause);
            System.out.println("<--------------end--------------->");
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("<--------------start------------->");
            System.out.println(message);
            System.out.println(replyCode);
            System.out.println(replyText);
            System.out.println(exchange);
            System.out.println(routingKey);
            System.out.println("<--------------end--------------->");
        });
    }

    @PostMapping("/send2")
    public void sendMsg2(String msg) {
        rabbitTemplate.convertAndSend("direct_exchange", "ssss", msg, new CorrelationData("123"));
    }
}
