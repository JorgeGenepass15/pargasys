package pe.com.pargasys.backend.service;

import pe.com.pargasys.backend.model.dto.TrackingDTO;
import pe.com.pargasys.backend.model.response.Response;

public interface TrackingService {

    Response<TrackingDTO> findByRequestNumber(String requestNumber);

}
