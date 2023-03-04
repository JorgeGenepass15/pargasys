package pe.com.pargasys.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.pargasys.backend.model.entity.ChargeEntity;

@Repository
public interface ChargeRepository extends JpaRepository<ChargeEntity, Long> {
}
