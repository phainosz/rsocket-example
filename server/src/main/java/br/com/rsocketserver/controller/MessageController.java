package br.com.rsocketserver.controller;

import br.com.rsocketserver.model.request.MessageRequest;
import br.com.rsocketserver.model.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class MessageController {

    @MessageMapping(value = "message.{name}")
    public Mono<MessageResponse> getMessage(@DestinationVariable("name") String name,
                                            @Payload MessageRequest messageRequest) {
        log.info("Generating one message");

        return Mono.just(new MessageResponse(messageRequest.getMessage() + name));
    }

    @MessageMapping("message")
    public Mono<Void> logMessage(@Payload MessageRequest messageRequest) {
        log.info("Message received: {}", messageRequest.getMessage());
        return Mono.empty();//or just void method
    }
}
