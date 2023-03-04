package pe.com.pargasys.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "charges", schema = "db_pargasys")
public class ChargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "company_id")
    private Integer companyId;

    public ChargeEntity(Integer companyId, String status) {
        this.companyId = companyId;
        this.status = status;
    }

}
