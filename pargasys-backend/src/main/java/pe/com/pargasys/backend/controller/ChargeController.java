package pe.com.pargasys.backend.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.pargasys.backend.model.dto.TrackingDTO;
import pe.com.pargasys.backend.model.response.ResponseList;
import pe.com.pargasys.backend.service.ChargeService;
import java.util.List;

@RestController
@RequestMapping(value = "/charges")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;
    @Autowired
    private MeterRegistry meterRegistry;

    @Operation(summary = "Realiza la carga de los pedidos.")
    @PostMapping(value = "/v1/uploadTrackings/{ruc}")
    @Timed(value = "pargasys.charges.uploadTrackings.post")
    public ResponseEntity<ResponseList<TrackingDTO>> uploadTrackings(@RequestBody List<TrackingDTO> trackings, @PathVariable String ruc) {
        meterRegistry.counter("pargasys.charges.uploadTrackings").increment();
        return new ResponseEntity<>(chargeService.uploadTrackings(trackings, ruc), HttpStatus.OK);
    }

}
