package com.example.demo.api;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author jrojas
 */
@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate template;

//    @PostMapping("/v1/api/consumer")
    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/producer")
    public String sendMessage(@RequestBody MessageDto messageDto) {
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        template.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, messageDto);
        return "Mensaje enviado";
    }
}
