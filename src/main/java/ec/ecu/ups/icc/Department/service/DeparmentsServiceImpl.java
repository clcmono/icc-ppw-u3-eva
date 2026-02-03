package ec.ecu.ups.icc.Department.service;

import ec.ecu.ups.icc.Department.dto.DepartmentWithEmployeesDto;
import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import ec.ecu.ups.icc.Department.repository.DeparmentsRepository;
import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import ec.ecu.ups.icc.employees.repository.EmployeesRepository;
import ec.ecu.ups.icc.exceptions.domain.BadRequestException;
import ec.ecu.ups.icc.exceptions.domain.NotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeparmentsServiceImpl implements DeparmentsService {

    private final DeparmentsRepository deparmentsRepository;
    private final EmployeesRepository employeesRepository;

    public DeparmentsServiceImpl(DeparmentsRepository deparmentsRepository,
            EmployeesRepository employeesRepository) {
        this.deparmentsRepository = deparmentsRepository;
        this.employeesRepository = employeesRepository;
    }

    @Override
    public DeparmentsEntity create(DeparmentsEntity department) {
        return deparmentsRepository.save(department);
    }

    @Override
    public DeparmentsEntity update(Long id, DeparmentsEntity department) {
        DeparmentsEntity existing = getById(id);
        existing.setName(department.getName());
        existing.setBudget(department.getBudget());
        existing.setCompany(department.getCompany());
        existing.setActive(department.getActive());
        return deparmentsRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public DeparmentsEntity getById(Long id) {
        return deparmentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found or inactive"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeparmentsEntity> getAll() {
        return deparmentsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeparmentsEntity> getByCompanyId(Long companyId) {
        return deparmentsRepository.findByCompanyId(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentWithEmployeesDto getDepartmentWithEmployees(Long id, String sort) {
        DeparmentsEntity department = getById(id);
        if (!"S".equalsIgnoreCase(department.getActive())) {
            throw new NotFoundException("Department not found or inactive");
        }
        Sort sortObj = buildSalarySort(sort);
        List<EmployeesEntity> employees = employeesRepository
                .findByDepartmentIdAndActive(id, "S", sortObj);
        return toDepartmentWithEmployeesDto(department, employees);
    }

    @Override
    public void delete(Long id) {
        DeparmentsEntity existing = getById(id);
        existing.setActive("N");
        deparmentsRepository.save(existing);
    }

    private Sort buildSalarySort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Order.desc("salary"));
        }
        if ("asc".equalsIgnoreCase(sort)) {
            return Sort.by(Sort.Order.asc("salary"));
        }
        if ("desc".equalsIgnoreCase(sort)) {
            return Sort.by(Sort.Order.desc("salary"));
        }
        throw new BadRequestException("Parametro sort invalido. Use 'asc' o 'desc'.");
    }

    private DepartmentWithEmployeesDto toDepartmentWithEmployeesDto(
            DeparmentsEntity department,
            List<EmployeesEntity> employees
    ) {
        DepartmentWithEmployeesDto dto = new DepartmentWithEmployeesDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setBudget(department.getBudget());

        DepartmentWithEmployeesDto.CompanyDto companyDto = new DepartmentWithEmployeesDto.CompanyDto();
        companyDto.setId(department.getCompany().getId());
        companyDto.setName(department.getCompany().getName());
        companyDto.setCountry(department.getCompany().getCountry());
        dto.setCompany(companyDto);

        List<DepartmentWithEmployeesDto.EmployeeDto> employeeDtos = new ArrayList<>();
        BigDecimal totalSalaries = BigDecimal.ZERO.setScale(2);
        for (EmployeesEntity employee : employees) {
            DepartmentWithEmployeesDto.EmployeeDto employeeDto =
                    new DepartmentWithEmployeesDto.EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setSalary(employee.getSalary());
            employeeDtos.add(employeeDto);
            if (employee.getSalary() != null) {
                totalSalaries = totalSalaries.add(employee.getSalary());
            }
        }
        dto.setEmployees(employeeDtos);
        dto.setEmployeeCount(employeeDtos.size());
        dto.setTotalSalaries(totalSalaries);
        return dto;
    }
}
