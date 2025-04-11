package talgat.home.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import talgat.home.dto.EmployeeDto;
import talgat.home.entity.Company;
import talgat.home.entity.Employee;
import talgat.home.entity.Person;
import talgat.home.repository.EmployeeRepository;
import talgat.home.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void shouldSuccessfullySaveEmployeeOrUpdate() {
        EmployeeDto dto = new EmployeeDto(0L, "John", "Doe", "john@mail.com", 1);
        Employee employee = new Employee(0L, "John", "Doe", "john@mail.com", new Person(), new Company(1, "", null));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto savedDto = employeeService.createEmployee(dto);

        assertEquals(dto.firstName(), savedDto.firstName());
        assertEquals(dto.lastName(), savedDto.lastName());
        assertEquals(dto.email(), savedDto.email());

        // how many times was called
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void shouldSuccessfullyGetAllEmployees() {
        Employee employee1 = new Employee(1L, "John", "Doe", "john@mail.com", new Person(), new Company(1, "", null));
        Employee employee2 = new Employee(2L, "John1", "Doe2", "john2@mail.com", new Person(), new Company(1, "", null));
        Employee employee3 = new Employee(2L, "John1", "Doe2", "john2@mail.com", new Person(), new Company(1, "", null));

        var employees = List.of(employee1, employee2, employee3);

        when(employeeRepository.findAll()).thenReturn(employees);
        var dtos = employeeService.getAllEmployees();
        assertEquals(3, dtos.size());
        assertEquals(employee1.getId(), dtos.get(0).id());
        assertEquals(employee2.getLastName(), dtos.get(1).lastName());
        assertEquals(employee3.getFirstName(), dtos.get(2).firstName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void shouldSuccessfullyGetEmployeeById() {
        Long employeeId = 1L;
        Employee employee = new Employee(1L, "John", "Doe", "john@mail.com", new Person(), new Company(1, "", null));
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
        assertEquals(employeeDto.firstName(), employee.getFirstName());
        assertEquals(employeeDto.lastName(), employee.getLastName());
        assertEquals(employeeDto.email(), employee.getEmail());
        assertEquals(employeeDto.companyId(), employee.getCompany().getId());
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void shouldSuccessfullyFindByEmail() {
        String email = "john@mail.com";
        Employee employee = new Employee(1L, "John", "Doe", "john@mail.com", new Person(), new Company(1, "", null));
        var employees = List.of(employee);
        when(employeeRepository.findByEmailContainingIgnoreCase(email)).thenReturn(employees);

        var dtos = employeeService.findByEmail(email);
        assertEquals(1, dtos.size());
        assertEquals(employee.getFirstName(), dtos.get(0).firstName());
        assertEquals(employee.getLastName(), dtos.get(0).lastName());
        assertEquals(employee.getEmail(), dtos.get(0).email());
        assertEquals(employee.getCompany().getId(), dtos.get(0).companyId());
        verify(employeeRepository, times(1)).findByEmailContainingIgnoreCase(email);
    }
}