package ec.ecu.ups.icc.Department.controller;

import ec.ecu.ups.icc.Department.dto.DepartmentWithEmployeesDto;
import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import ec.ecu.ups.icc.Department.service.DeparmentsService;
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
@RequestMapping("/api/departments")
public class DepartmentsController {
    private final DeparmentsService departmentsService;

    public DepartmentsController(DeparmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @PostMapping
    public DeparmentsEntity create(@RequestBody DeparmentsEntity department) {
        return departmentsService.create(department);
    }

    @GetMapping
    public List<DeparmentsEntity> getAll() {
        return departmentsService.getAll();
    }

    @GetMapping("/{id}")
    public DeparmentsEntity getById(@PathVariable Long id) {
        return departmentsService.getById(id);
    }

    @PutMapping("/{id}")
    public DeparmentsEntity update(@PathVariable Long id, @RequestBody DeparmentsEntity department) {
        return departmentsService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentsService.delete(id);
    }

    @GetMapping("/{id}/employees")
    public DepartmentWithEmployeesDto getDepartmentEmployees(
            @PathVariable Long id,
            @RequestParam(defaultValue = "desc") String sort
    ) {
        return departmentsService.getDepartmentWithEmployees(id, sort);
    }
}
