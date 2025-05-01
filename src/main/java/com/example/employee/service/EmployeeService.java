package com.example.employee.service;

import com.example.employee.Entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id){
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee){
       return employeeRepository.save(employee);

    }

    public void deleteEmployeeById(int id){
        employeeRepository.deleteById(id);
    }
}
