package pe.com.pargasys.backend.mapper;

import org.springframework.stereotype.Component;
import pe.com.pargasys.backend.model.entity.TrackingEntity;
import pe.com.pargasys.backend.model.dto.TrackingDTO;

@Component
public class Mapper {

    public TrackingDTO mapperToTrackingDTO(TrackingEntity tracking) {
        TrackingDTO trackingDTO = new TrackingDTO();
        trackingDTO.setNroPedido(tracking.getRequestNumber());
        trackingDTO.setConsignado(tracking.getConsigned());
        trackingDTO.setDireccion(tracking.getAddress());
        trackingDTO.setDestino(tracking.getDestination());
        trackingDTO.setTelefono(tracking.getPhone());
        trackingDTO.setDni(tracking.getDni());
        trackingDTO.setFechaRecepcion(tracking.getReceptionDate());
        trackingDTO.setFechaEstimada(tracking.getEstimatedDate());
        trackingDTO.setFechaVisita(tracking.getVisitDate());
        trackingDTO.setFechaEntrega(tracking.getDeliveryDate());
        trackingDTO.setEstado(tracking.getStatus());
        trackingDTO.setMotivo(tracking.getMotive());
        trackingDTO.setIncidencia(tracking.getIncidence());
        trackingDTO.setAgente(tracking.getCarrier());
        trackingDTO.setDescripcion(tracking.getDescription());
        trackingDTO.setCompaniaId(tracking.getCompanyId());
        trackingDTO.setCargaId(tracking.getChargeId());
        return trackingDTO;
    }

    public TrackingEntity mapperToTracking(TrackingDTO trackingDTO) {
        TrackingEntity tracking = new TrackingEntity();
        tracking.setRequestNumber(trackingDTO.getNroPedido());
        tracking.setConsigned(trackingDTO.getConsignado());
        tracking.setAddress(trackingDTO.getDireccion());
        tracking.setDestination(trackingDTO.getDestino());
        tracking.setPhone(trackingDTO.getTelefono());
        tracking.setDni(trackingDTO.getDni());
        tracking.setReceptionDate(trackingDTO.getFechaRecepcion());
        tracking.setEstimatedDate(trackingDTO.getFechaEstimada());
        tracking.setVisitDate(trackingDTO.getFechaVisita());
        tracking.setDeliveryDate(trackingDTO.getFechaEntrega());
        tracking.setStatus(trackingDTO.getEstado());
        tracking.setMotive(trackingDTO.getMotivo());
        tracking.setIncidence(trackingDTO.getIncidencia());
        tracking.setCarrier(trackingDTO.getAgente());
        tracking.setDescription(trackingDTO.getDescripcion());
        tracking.setCompanyId(trackingDTO.getCompaniaId());
        tracking.setChargeId(trackingDTO.getCargaId());
        return tracking;
    }

}
