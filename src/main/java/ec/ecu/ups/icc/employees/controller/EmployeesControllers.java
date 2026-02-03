package ec.ecu.ups.icc.employees.controller;

import ec.ecu.ups.icc.employees.dto.EmployeeTransferRequestDto;
import ec.ecu.ups.icc.employees.dto.EmployeeTransferResponseDto;
import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import ec.ecu.ups.icc.employees.service.EmployeesService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeesControllers {

    private final EmployeesService employeesService;

    public EmployeesControllers(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping
    public EmployeesEntity create(@RequestBody EmployeesEntity employee) {
        return employeesService.create(employee);
    }

    @GetMapping
    public List<EmployeesEntity> getAll() {
        return employeesService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeesEntity getById(@PathVariable Long id) {
        return employeesService.getById(id);
    }

    @PutMapping("/{id}")
    public EmployeesEntity update(@PathVariable Long id, @RequestBody EmployeesEntity employee) {
        return employeesService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeesService.delete(id);
    }

    @PatchMapping("/{employeeId}/transfer")
    public EmployeeTransferResponseDto transferEmployee(
            @PathVariable Long employeeId,
            @RequestBody EmployeeTransferRequestDto request
    ) {
        return employeesService.transferEmployee(employeeId, request.getNewDepartmentId());
    }
}
