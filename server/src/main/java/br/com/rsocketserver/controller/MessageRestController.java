package br.com.rsocketserver.controller;

import br.com.rsocketserver.model.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageRestController {

    @GetMapping
    public Mono<MessageResponse> getMessage() {
        log.info("Generating one rest message");

        return Mono.just(new MessageResponse("Awesome rest message!"));
    }
}
