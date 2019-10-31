package chenqian.site.rabbitmqboottest;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 功能简介:.
 *
 * @author cq
 * @version 1.0
 * *
 */
@Component
@Slf4j
public class ConsumerTest {

    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
     * 手动确认
     *
     * @param msg
     * @param channel
     * @param message
     * @throws InterruptedException
     * @throws IOException
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "pb_queue2", durable = "false"),
                    exchange = @Exchange(value = "pb_exchange", durable = "false", type = ExchangeTypes.FANOUT))
    })
    public void publishConsumer2(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        log.info(Thread.currentThread().getName() + msg);
        Thread.sleep(5000);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    @RabbitListener(queuesToDeclare = @Queue(value = "pb_queue2", durable = "false"))
    public void receiverMq(String msg) {
        log.info(Thread.currentThread().getName() + "接收到队列消息:{}", msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "bp_queue3", durable = "false"),
                    exchange = @Exchange(value = "direct_exchange", durable = "false"))
    })
    public void receiverDirectMq(String msg, Channel channel, Message message) {
        log.info("----start------");
        log.info(Thread.currentThread().getName() + "--" + channel.getChannelNumber() + "--" + message.getMessageProperties().getConsumerTag());
        log.info("----end--------");
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "bp_queue3", durable = "false"),
                    exchange = @Exchange(value = "direct_exchange", durable = "false", type = ExchangeTypes.FANOUT))
    })
    public void receiverMq(String msg, Channel channel, Message message) {
        log.info(Thread.currentThread().getName() + "--" + channel.getChannelNumber() + "--" + message.getMessageProperties().getConsumerTag());
    }

}
