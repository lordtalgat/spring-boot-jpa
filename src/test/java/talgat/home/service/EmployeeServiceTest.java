package talgat.home.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import talgat.home.dto.EmployeeDto;
import talgat.home.entity.Company;
import talgat.home.entity.Employee;
import talgat.home.entity.Person;
import talgat.home.repository.EmployeeRepository;
import talgat.home.service.impl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
        MockitoAnnotations.openMocks(this);
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

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        when(employeeRepository.findAll()).thenReturn(employees);
        List<EmployeeDto> dtos = employeeService.getAllEmployees();
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
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepository.findByEmailContainingIgnoreCase(email)).thenReturn(employees);

        List<EmployeeDto> dtos = employeeService.findByEmail(email);
        assertEquals(1, dtos.size());
        assertEquals(employee.getFirstName(), dtos.get(0).firstName());
        assertEquals(employee.getLastName(), dtos.get(0).lastName());
        assertEquals(employee.getEmail(), dtos.get(0).email());
        assertEquals(employee.getCompany().getId(), dtos.get(0).companyId());
        verify(employeeRepository, times(1)).findByEmailContainingIgnoreCase(email);
    }
}