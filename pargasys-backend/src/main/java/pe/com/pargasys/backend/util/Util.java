package pe.com.pargasys.backend.util;

import org.springframework.stereotype.Component;
import pe.com.pargasys.backend.model.entity.TrackingEntity;

@Component
public class Util {

    public TrackingEntity insertCompanyAndCharge(TrackingEntity tracking, Integer companyId, Long chargeId) {
        tracking.setCompanyId(companyId);
        tracking.setChargeId(chargeId.intValue());
        return tracking;
    }

}
