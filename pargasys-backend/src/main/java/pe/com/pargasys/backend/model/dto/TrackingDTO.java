package pe.com.pargasys.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TrackingDTO {

    @JsonProperty
    private String nroPedido;

    @JsonProperty
    private String consignado;

    @JsonProperty
    private String direccion;

    @JsonProperty
    private String destino;

    @JsonProperty
    private String telefono;

    @JsonProperty
    private String dni;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private LocalDate fechaRecepcion;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private LocalDate fechaEstimada;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private LocalDate fechaVisita;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private LocalDate fechaEntrega;

    @JsonProperty
    private String estado;

    @JsonProperty
    private String motivo;

    @JsonProperty
    private String incidencia;

    @JsonProperty
    private String agente;

    @JsonProperty
    private String descripcion;

    @JsonProperty
    private Integer companiaId;

    @JsonProperty
    private Integer cargaId;

}
