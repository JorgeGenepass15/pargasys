package pe.com.pargasys.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "trackings", schema = "db_pargasys")
public class TrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_number")
    private String requestNumber;

    @Column(name = "consigned")
    private String consigned;

    @Column(name = "address")
    private String address;

    @Column(name = "destination")
    private String destination;

    @Column(name = "phone")
    private String phone;

    @Column(name = "dni")
    private String dni;

    @Column(name = "reception_date")
    private LocalDate receptionDate;

    @Column(name = "estimated_date")
    private LocalDate estimatedDate;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "status")
    private String status;

    @Column(name = "motive")
    private String motive;

    @Column(name = "incidence")
    private String incidence;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "description")
    private String description;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "charge_id")
    private Integer chargeId;

}
