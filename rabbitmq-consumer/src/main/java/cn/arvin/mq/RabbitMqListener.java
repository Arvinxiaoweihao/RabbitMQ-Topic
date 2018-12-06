package cn.arvin.mq;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author weihai.xiao
 * @version 1.0
 * @date 2018/11/27 22:32
 */
@Component
public class RabbitMqListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "rabbit.queue", durable = "true"),
            exchange = @Exchange(value = "rabbit.exchange",type = ExchangeTypes.TOPIC,
                    ignoreDeclarationExceptions = "true"),
            key = {"send.check.code"}))
    public void listenVerifyCode(Map<String,String> msg){
        String email = msg.get("email");
        String checkCode = msg.get("checkCode");
        try {
            if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(checkCode)) {
                System.out.println("code = " + checkCode);
                System.out.println("email = " + email);
            }
        }catch (Exception e){
            logger.error("发送失败，email:{}, checkCode:{}", email, checkCode, e);
        }
    }
}
