package ec.ecu.ups.icc.Department.repository;

import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeparmentsRepository extends JpaRepository<DeparmentsEntity, Long> {
    List<DeparmentsEntity> findByCompanyId(Long companyId);

    List<DeparmentsEntity> findByCompanyIdAndActive(Long companyId, String active);
}
