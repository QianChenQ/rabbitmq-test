

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 功能简介:.
 *
 * @author cq
 * @version 1.0
 * *
 */
public class PublishProduce {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.44.128");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        //channel.exchangeDeclare("pb_exchange", "fanout");
        //channel.queueDeclare("pb_queue3", false, false, false, null);
        //channel.queueBind("pb_queue3", "pb_exchange", "");
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("direct_exchange", "", null, ("发布消息" + i).getBytes());
        }
        channel.close();
        connection.close();

    }
}
