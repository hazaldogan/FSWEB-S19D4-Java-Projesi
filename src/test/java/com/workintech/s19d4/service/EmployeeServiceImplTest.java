package com.workintech.s19d4.service;

import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findByOrder() {
        employeeService.findByOrder();
        verify(employeeRepository).findByOrder();
    }

    @Test
    void isSaveSucceed() {
        Employee employee = new Employee();
        employee.setEmail("deneme@gmail.com");
        employee.setFirstName("emrah");
        employee.setLastName("test");
        given(employeeRepository.findByEmail("deneme@gmail.com")).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee saved = employeeService.save(employee);
        assertNotNull(saved);
    }

    @Test
    void isSaveFailed() {
        Employee employee = new Employee();
        employee.setEmail("deneme@gmail.com");
        employee.setFirstName("emrah");
        employee.setLastName("test");
        given(employeeRepository.findByEmail("deneme@gmail.com")).willReturn(Optional.of(employee));
        Employee saved = employeeService.save(employee);
        assertNull(saved);
    }
}