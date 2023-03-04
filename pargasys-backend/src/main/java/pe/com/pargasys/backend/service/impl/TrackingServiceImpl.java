package pe.com.pargasys.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.com.pargasys.backend.constant.Constant;
import pe.com.pargasys.backend.mapper.Mapper;
import pe.com.pargasys.backend.model.dto.TrackingDTO;
import pe.com.pargasys.backend.model.response.Response;
import pe.com.pargasys.backend.repository.TrackingRepository;
import pe.com.pargasys.backend.service.TrackingService;

import java.util.List;

@Service
@Slf4j
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private Mapper mapper;

    @Override
    //@Cacheable("tracking")
    public Response<TrackingDTO> findByRequestNumber(String requestNumber) {
        log.info("Start - Function find by request number: [request: {}]", requestNumber);
        TrackingDTO trackingDTO = mapper.mapperToTrackingDTO(trackingRepository.findByRequestNumber(requestNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.NF_TRACKING)));
        log.info("End - Function find by request number: [response: {}]", trackingDTO);
        return Response.success(trackingDTO);
    }

    @KafkaListener(
            id = "pargasys",
            autoStartup = "false",
            topics = "pargasys-topic",
            containerFactory = "listenerContainerFactory",
            groupId = "pargasys-group",
            properties = { "max.poll.interval.ms:4000", "max.poll.records:10" })
    public void listenKafka(List<ConsumerRecord<String, String>> messages) {
        messages.stream()
                .peek(m -> log.info("partition: {}, offset: {}, key: {}, value: {}", m.partition(), m.offset(), m.key(), m.value()));
    }

}
