package ec.ecu.ups.icc.employees.service;

import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import ec.ecu.ups.icc.Department.repository.DeparmentsRepository;
import ec.ecu.ups.icc.employees.dto.EmployeeTransferResponseDto;
import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import ec.ecu.ups.icc.employees.repository.EmployeesRepository;
import ec.ecu.ups.icc.exceptions.domain.ConflictException;
import ec.ecu.ups.icc.exceptions.domain.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;
    private final DeparmentsRepository deparmentsRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository,
            DeparmentsRepository deparmentsRepository) {
        this.employeesRepository = employeesRepository;
        this.deparmentsRepository = deparmentsRepository;
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
    public EmployeeTransferResponseDto transferEmployee(Long employeeId, Long newDepartmentId) {
        EmployeesEntity employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found or inactive"));
        if (!"S".equalsIgnoreCase(employee.getActive())) {
            throw new NotFoundException("Employee not found or inactive");
        }

        DeparmentsEntity newDepartment = deparmentsRepository.findById(newDepartmentId)
                .orElseThrow(() -> new NotFoundException("Department not found or inactive"));
        if (!"S".equalsIgnoreCase(newDepartment.getActive())) {
            throw new NotFoundException("Department not found or inactive");
        }

        DeparmentsEntity oldDepartment = employee.getDepartment();
        if (oldDepartment != null && oldDepartment.getId().equals(newDepartment.getId())) {
            throw new ConflictException("Employee already in that department");
        }

        employee.setDepartment(newDepartment);
        EmployeesEntity saved = employeesRepository.save(employee);

        EmployeeTransferResponseDto dto = new EmployeeTransferResponseDto();
        dto.setEmployeeId(saved.getId());
        dto.setEmployeeName(saved.getFirstName() + " " + saved.getLastName());

        EmployeeTransferResponseDto.DepartmentSimpleDto oldDeptDto =
                new EmployeeTransferResponseDto.DepartmentSimpleDto();
        if (oldDepartment != null) {
            oldDeptDto.setId(oldDepartment.getId());
            oldDeptDto.setName(oldDepartment.getName());
        }
        dto.setOldDepartment(oldDeptDto);

        EmployeeTransferResponseDto.DepartmentSimpleDto newDeptDto =
                new EmployeeTransferResponseDto.DepartmentSimpleDto();
        newDeptDto.setId(newDepartment.getId());
        newDeptDto.setName(newDepartment.getName());
        dto.setNewDepartment(newDeptDto);

        dto.setMessage("Employee transferred successfully");
        return dto;
    }

    @Override
    public void delete(Long id) {
        EmployeesEntity existing = getById(id);
        existing.setActive("N");
        employeesRepository.save(existing);
    }
}
