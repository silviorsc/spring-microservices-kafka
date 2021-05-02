package com.hatanaka.ecommerce.checkout.resource.checkout;

import com.hatanaka.ecommerce.checkout.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutResource {

    private final CheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody CheckoutRequest checkoutRequest) {
  /*      final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode("123")
                .build();
//        checkoutCreatedSource.output().send(MessageBuilder.withPayload("asdasdasd").build());
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());*/

       // checkoutService.create(checkouRequest)
        return ResponseEntity.ok().build();
    }
}
