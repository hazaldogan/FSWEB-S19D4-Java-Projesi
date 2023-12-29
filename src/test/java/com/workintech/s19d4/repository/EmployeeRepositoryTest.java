package com.workintech.s19d4.repository;

import com.workintech.s19d4.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Hazal");
        employee1.setLastName("Taştan");
        employee1.setEmail("hzl@test.com");
        employee1.setSalary(2200d);

        Employee employee2 = new Employee();
        employee2.setFirstName("Buğra");
        employee2.setLastName("Taştan");
        employee2.setEmail("bgr@test.com");
        employee2.setSalary(4000d);

        Employee employee3 = new Employee();
        employee3.setFirstName("Umut");
        employee3.setLastName("Taştan");
        employee3.setEmail("umut@test.com");
        employee3.setSalary(1000d);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        employeeRepository.saveAll(employeeList);

    }

    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }

    @Test
    @DisplayName(value="find employee by email user tests")
    void findByEmail() {
        String nonExistEmail = "hazal@test.com";
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(nonExistEmail);
        assertEquals(Optional.empty(),optionalEmployee);

        String existEmail = "hzl@test.com";
        Optional<Employee> optionalEmployee1 = employeeRepository.findByEmail(existEmail);
        assertNotNull(optionalEmployee1.get());
        assertEquals("Hazal",optionalEmployee1.get().getFirstName());
        assertEquals(2200d,optionalEmployee1.get().getSalary());
    }

    @Test
    void findBySalary() {
        List<Employee> employeeList = employeeRepository.findBySalary(2500);
        assertEquals(1,employeeList.size());
    }

    @Test
    void findByOrder() {
        List<Employee> employeeList = employeeRepository.findByOrder();
        assertEquals(3,employeeList.size());
        assertEquals("Hazal", employeeList.get(0).getFirstName());
    }
}