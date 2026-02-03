package ec.ecu.ups.icc.Company.repository;

import ec.ecu.ups.icc.Company.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepository extends JpaRepository<Companies, Long> {
}
