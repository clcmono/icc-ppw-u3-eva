package ec.ecu.ups.icc.Company.controller;

import ec.ecu.ups.icc.Company.dto.CompanyDepartmentsDto;
import ec.ecu.ups.icc.Company.entity.Companies;
import ec.ecu.ups.icc.Company.service.CompaniesService;
import ec.ecu.ups.icc.employees.dto.EmployeesResponseDto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompaniesService companyService;
    public CompanyController(CompaniesService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Companies create(@RequestBody Companies companies) {
        return companyService.create(companies);
    }

    @GetMapping
    public List<Companies> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public Companies getById(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @PutMapping("/{id}")
    public Companies update(@PathVariable Long id, @RequestBody Companies companies) {
        return companyService.update(id, companies);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

    @GetMapping("/{id}/departments")
    public CompanyDepartmentsDto getCompanyDepartments(@PathVariable Long id) {
        return companyService.getCompanyDepartments(id);
    }

    @GetMapping("/{id}/high-salary-employees")
    public EmployeesResponseDto getHighSalaryEmployees(
            @PathVariable Long id,
            @RequestParam(required = false) BigDecimal minSalary
    ) {
        return companyService.getHighSalaryEmployees(id, minSalary);
    }
}
