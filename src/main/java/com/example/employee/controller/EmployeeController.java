package com.example.employee.controller;

import com.example.employee.Entity.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{/id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("{/id}")
    public ResponseEntity<Employee> saveEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Employee employeeToUpdate = existingEmployee.get();
        employeeToUpdate.setId(employee.getId());
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setAge(employee.getAge());

        Employee savedEmployee = employeeService.saveEmployee(employeeToUpdate);
        return ResponseEntity.ok(savedEmployee);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id){
        Optional<Employee> existingEmployee=employeeService.getEmployeeById(id);
        if(existingEmployee.isPresent()){
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
