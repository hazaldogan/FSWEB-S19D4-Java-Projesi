package com.workintech.s19d4.service;

import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public Employee save(Employee employee) {
        Employee foundByEmail = findByEmail(employee.getEmail());
        if(foundByEmail != null){
            return null;
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(long id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public List<Employee> findBySalary(double salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }
}
