package pe.com.pargasys.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.pargasys.backend.model.entity.TrackingEntity;

import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {

    Optional<TrackingEntity> findByRequestNumber(String requestNumber);

}
