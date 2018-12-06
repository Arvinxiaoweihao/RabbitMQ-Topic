package cn.arvin.web;

import cn.arvin.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weihai.xiao
 * @version 1.0
 * @date 2018/11/27 22:20
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {

    @Autowired
    private RabbitMqService rabbitMqService;

    @GetMapping("send/{email}/{checkCode}")
    public void sendCheckCode(@PathVariable String email,@PathVariable String checkCode){
        rabbitMqService.sendCheckCode(email,checkCode);
    }
}
