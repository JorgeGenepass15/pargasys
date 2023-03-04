package pe.com.pargasys.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.com.pargasys.backend.constant.Constant;
import pe.com.pargasys.backend.model.entity.ChargeEntity;
import pe.com.pargasys.backend.mapper.Mapper;
import pe.com.pargasys.backend.model.dto.TrackingDTO;
import pe.com.pargasys.backend.model.response.ResponseList;
import pe.com.pargasys.backend.repository.ChargeRepository;
import pe.com.pargasys.backend.repository.CompanyRepository;
import pe.com.pargasys.backend.repository.TrackingRepository;
import pe.com.pargasys.backend.service.ChargeService;
import pe.com.pargasys.backend.util.Util;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private Util util;
    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public ResponseList<TrackingDTO> uploadTrackings(List<TrackingDTO> trackings, String ruc) {
        log.info("Start - Function upload trackings: [request: {}, {}]", trackings, ruc);
        Integer companyId = companyRepository.findByRuc(ruc)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.NF_COMPANY))
                .getId()
                .intValue();
        log.info("Get companyId: {}", companyId);
        ChargeEntity charge = new ChargeEntity(companyId, Constant.CHARGE_STATUS);
        Long chargeId = chargeRepository.save(charge)
                .getId();
        log.info("Get new chargeId: {}", chargeId);
        List<TrackingDTO> trackingsDTO = trackingRepository.saveAll(trackings.stream()
                        .map(trackingDTO -> mapper.mapperToTracking(trackingDTO))
                        .map(tracking -> util.insertCompanyAndCharge(tracking, companyId, chargeId))
                        .collect(Collectors.toList()))
                .stream()
                .map(mapper::mapperToTrackingDTO)
                .collect(Collectors.toList());
        log.info("End - Function upload trackings: [response: {}]", trackingsDTO);
        return ResponseList.success(trackingsDTO);
    }

}
