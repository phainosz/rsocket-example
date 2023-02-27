package br.com.rsocketclient.controller;

import br.com.rsocketclient.model.request.MessageRequest;
import br.com.rsocketclient.model.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final RSocketRequester rSocketRequester;

    public MessageController(RSocketRequester.Builder rSocketBuilder) {
        this.rSocketRequester = rSocketBuilder.tcp("localhost", 7000);
    }

    @GetMapping
    public Mono<MessageResponse> getMessage() {
        log.info("Retrieve one message");

        return this.rSocketRequester.route("message.{name}", "Jon")
                .data(new MessageRequest("Hello my friend "))
                .retrieveMono(MessageResponse.class);
    }

    @GetMapping("/forget")
    public Mono<Void> fireAndForge() {
        log.info("Fire and Forget");

        return this.rSocketRequester.route("message")
                .data(new MessageRequest("Log this and forget."))
                .retrieveMono(Void.class);
    }
}
