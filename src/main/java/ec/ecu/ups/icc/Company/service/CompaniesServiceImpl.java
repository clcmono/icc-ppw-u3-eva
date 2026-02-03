package ec.ecu.ups.icc.Company.service;

import ec.ecu.ups.icc.Company.dto.CompanyDepartmentsDto;
import ec.ecu.ups.icc.Company.entity.Companies;
import ec.ecu.ups.icc.Company.repository.CompaniesRepository;
import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import ec.ecu.ups.icc.Department.repository.DeparmentsRepository;
import ec.ecu.ups.icc.employees.repository.EmployeesRepository;
import ec.ecu.ups.icc.employees.dto.EmployeesResponseDto;
import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import ec.ecu.ups.icc.exceptions.domain.NotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompaniesServiceImpl implements CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final DeparmentsRepository deparmentsRepository;
    private final EmployeesRepository employeesRepository;

    public CompaniesServiceImpl(CompaniesRepository companiesRepository,
            DeparmentsRepository deparmentsRepository,
            EmployeesRepository employeesRepository) {
        this.companiesRepository = companiesRepository;
        this.deparmentsRepository = deparmentsRepository;
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Companies create(Companies companies) {
        return companiesRepository.save(companies);
    }

    @Override
    public Companies update(Long id, Companies companies) {
        Companies existing = getById(id);
        existing.setName(companies.getName());
        existing.setCountry(companies.getCountry());
        existing.setActive(companies.getActive());
        return companiesRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Companies getById(Long id) {
        return companiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Companies> getAll() {
        return companiesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDepartmentsDto getCompanyDepartments(Long id) {
        Companies company = getById(id);
        if (!"S".equalsIgnoreCase(company.getActive())) {
            throw new NotFoundException("Company not found or inactive");
        }

        List<DeparmentsEntity> departments =
                deparmentsRepository.findByCompanyIdAndActive(id, "S");

        CompanyDepartmentsDto dto = new CompanyDepartmentsDto();
        dto.setCompanyId(company.getId());
        dto.setCompanyName(company.getName());
        dto.setCountry(company.getCountry());

        List<CompanyDepartmentsDto.DepartmentWithCountDto> deptDtos = new ArrayList<>();
        BigDecimal totalBudget = BigDecimal.ZERO.setScale(2);

        for (DeparmentsEntity department : departments) {
            CompanyDepartmentsDto.DepartmentWithCountDto deptDto =
                    new CompanyDepartmentsDto.DepartmentWithCountDto();
            deptDto.setId(department.getId());
            deptDto.setName(department.getName());
            deptDto.setBudget(department.getBudget());

            int employeeCount =
                    employeesRepository.countByDepartmentIdAndActive(department.getId(), "S");
            deptDto.setEmployeeCount(employeeCount);
            deptDtos.add(deptDto);

            if (department.getBudget() != null) {
                totalBudget = totalBudget.add(department.getBudget());
            }
        }

        dto.setDepartments(deptDtos);
        dto.setDepartmentCount(deptDtos.size());
        dto.setTotalBudget(totalBudget);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeesResponseDto getHighSalaryEmployees(Long id, BigDecimal minSalary) {
        Companies company = getById(id);
        if (!"S".equalsIgnoreCase(company.getActive())) {
            throw new NotFoundException("Company not found or inactive");
        }

        BigDecimal min = minSalary != null ? minSalary : new BigDecimal("5000.00");

        List<EmployeesEntity> employees =
                employeesRepository.findByDepartmentCompanyIdAndActiveAndDepartmentActiveAndSalaryGreaterThanEqual(
                        id,
                        "S",
                        "S",
                        min,
                        Sort.by(Sort.Order.desc("salary"))
                );

        EmployeesResponseDto dto = new EmployeesResponseDto();
        dto.setCompanyName(company.getName());
        dto.setMinSalary(min);

        List<EmployeesResponseDto.EmployeeWithDepartmentDto> employeeDtos = new ArrayList<>();
        for (EmployeesEntity employee : employees) {
            EmployeesResponseDto.EmployeeWithDepartmentDto empDto =
                    new EmployeesResponseDto.EmployeeWithDepartmentDto();
            empDto.setId(employee.getId());
            empDto.setFirstName(employee.getFirstName());
            empDto.setLastName(employee.getLastName());
            empDto.setEmail(employee.getEmail());
            empDto.setSalary(employee.getSalary());

            EmployeesResponseDto.DepartmentSimpleDto deptDto =
                    new EmployeesResponseDto.DepartmentSimpleDto();
            deptDto.setId(employee.getDepartment().getId());
            deptDto.setName(employee.getDepartment().getName());
            empDto.setDepartment(deptDto);

            employeeDtos.add(empDto);
        }
        dto.setEmployees(employeeDtos);
        dto.setCount(employeeDtos.size());
        return dto;
    }

    @Override
    public void delete(Long id) {
        Companies existing = getById(id);
        existing.setActive("N");
        companiesRepository.save(existing);
    }
}
