package cn.arvin.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author weihai.xiao
 * @version 1.0
 * @date 2018/11/27 22:19
 */
@Service
public class RabbitMqService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendCheckCode(String email, String checkCode) {
        HashMap<String, String> message = new HashMap<>();
        try {
            message.put("email",email);
            message.put("checkCode",checkCode);
            System.out.println("start send message !!!!");
            amqpTemplate.convertAndSend("rabbit.exchange","send.check.code",message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            message.clear();
        }

    }
}
