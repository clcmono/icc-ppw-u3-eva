package ec.ecu.ups.icc.employees.service;

import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import ec.ecu.ups.icc.employees.repository.EmployeesRepository;
import ec.ecu.ups.icc.exceptions.domain.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public EmployeesEntity create(EmployeesEntity employee) {
        return employeesRepository.save(employee);
    }

    @Override
    public EmployeesEntity update(Long id, EmployeesEntity employee) {
        EmployeesEntity existing = getById(id);
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setSalary(employee.getSalary());
        existing.setDepartment(employee.getDepartment());
        existing.setActive(employee.getActive());
        return employeesRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeesEntity getById(Long id) {
        return employeesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeesEntity> getAll() {
        return employeesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeesEntity> getByDepartmentId(Long departmentId) {
        return employeesRepository.findByDepartmentId(departmentId);
    }

    @Override
    public void delete(Long id) {
        EmployeesEntity existing = getById(id);
        existing.setActive("N");
        employeesRepository.save(existing);
    }
}
