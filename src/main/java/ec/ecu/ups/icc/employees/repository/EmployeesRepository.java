package ec.ecu.ups.icc.employees.repository;

import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Long> {
    List<EmployeesEntity> findByDepartmentId(Long departmentId);

    List<EmployeesEntity> findByDepartmentIdAndActive(Long departmentId, String active, Sort sort);

    int countByDepartmentIdAndActive(Long departmentId, String active);

    List<EmployeesEntity> findByDepartmentCompanyIdAndActiveAndDepartmentActiveAndSalaryGreaterThanEqual(
            Long companyId,
            String active,
            String departmentActive,
            java.math.BigDecimal minSalary,
            Sort sort
    );
}
