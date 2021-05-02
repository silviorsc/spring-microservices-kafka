package com.hatanaka.ecommerce.checkout.service;

import com.hatanaka.ecommerce.checkout.entity.CheckoutEntity;
import com.hatanaka.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.hatanaka.ecommerce.checkout.repository.CheckoutRepository;
import com.hatanaka.ecommerce.checkout.resource.checkout.CheckoutRequest;
import com.hatanaka.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService{


    private final CheckoutRepository checkoutRepository;

    private final CheckoutCreatedSource checkoutCreatedSource;

    //Injecao de dependencia via construtor - retirado porque será utilizado @RequiredArgsConstructor

    /*public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }*/

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
       final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
               .code(UUID.randomUUID().toString()).status(CheckoutEntity.Status.CREATED)
               .build();

       //salva a entidade
       final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);

       //envia a mensagem com o evento criado
        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(checkoutRepository.save(checkoutEntity));
    }
}
