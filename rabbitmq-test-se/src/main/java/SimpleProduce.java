

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *功能简介:.
 * @author cq
 * @version 1.0
 * * */
public class SimpleProduce {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.44.128");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("first_queue", false, false, false, null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","first_queue",null,("first message" + i).getBytes());

        }
        System.out.println("message is done");
        channel.close();
        connection.close();

    }
}
