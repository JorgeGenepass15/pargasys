package pe.com.pargasys.backend.service;

import pe.com.pargasys.backend.model.dto.TrackingDTO;
import pe.com.pargasys.backend.model.response.ResponseList;

import java.util.List;

public interface ChargeService {

    ResponseList<TrackingDTO> uploadTrackings(List<TrackingDTO> trackings, String ruc);

}
