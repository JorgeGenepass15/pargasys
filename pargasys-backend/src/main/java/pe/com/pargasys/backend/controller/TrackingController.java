package pe.com.pargasys.backend.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import pe.com.pargasys.backend.model.dto.TrackingDTO;
import pe.com.pargasys.backend.model.response.Response;
import pe.com.pargasys.backend.service.TrackingService;

@RestController
@RequestMapping(value = "/trackings")
@Slf4j
public class TrackingController {

    @Autowired
    private TrackingService trackingService;
    @Autowired
    private MeterRegistry meterRegistry;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Operation(summary = "Buscar pedido por numero de pedido.")
    @GetMapping(value = "/v1/findByRequestNumber/{requestNumber}")
    @Timed(value = "pargasys.trackings.findByRequestNumber.get")
    public ResponseEntity<Response<TrackingDTO>> findByRequestNumber(@PathVariable String requestNumber) {
        meterRegistry.counter("pargasys.trackings.findByRequestNumber").increment();
        return new ResponseEntity<>(trackingService.findByRequestNumber(requestNumber), HttpStatus.OK);
    }

    @Operation(summary = "Buscar pedido por numero de pedido con kafka.")
    @GetMapping(value = "/v1/findByRequestNumberKafka/{requestNumber}")
    public void findByRequestNumberKafka(@PathVariable String requestNumber) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("pargasys-topic", requestNumber);
        future.addCallback(new KafkaSendCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Message send: {}", result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Error sending message: {}", ex.getMessage());
            }

            @Override
            public void onFailure(KafkaProducerException e) {
                log.error("Error sending message: {}", e.getMessage());
            }

        });
        registry.getListenerContainer("pargasys").start();
    }

}
