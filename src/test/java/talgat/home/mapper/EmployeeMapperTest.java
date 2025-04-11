package talgat.home.mapper;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import talgat.home.dto.EmployeeDto;
import talgat.home.entity.Company;
import talgat.home.entity.Employee;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {

    @Test
    public void shouldMapEmployeeDtoToEmployee() {
        EmployeeDto dto = new EmployeeDto(0L, "John", "Doe", "john@mail.com", 1);
        Employee employee = EmployeeMapper.mapToEmployee(dto);
        assertNotNull(employee);
        assertEquals(dto.firstName(), employee.getFirstName());
        assertEquals(dto.lastName(), employee.getLastName());
        assertEquals(dto.email(), employee.getEmail());
        assertEquals(dto.companyId(), employee.getCompany().getId());
    }

    @Test
    public void shouldMapEmployeeToEmployeeDto() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john@mail.com");
        Company company = new Company();
        company.setId(1);
        employee.setCompany(company);
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        assertNotNull(employeeDto);
        assertEquals(employeeDto.firstName(), employee.getFirstName());
        assertEquals(employeeDto.lastName(), employee.getLastName());
        assertEquals(employeeDto.email(), employee.getEmail());
        assertEquals(employeeDto.companyId(), employee.getCompany().getId());
    }

    @Test
    public void shouldThrowExceptionIfEmployeeDtoIsNull() {
        var exp = assertThrows(NullPointerException.class, () -> EmployeeMapper.mapToEmployee(null));
        assertEquals("Employee Dto is null", exp.getMessage());
    }

    @Test void shouldThrowExceptionIfEmployeeIsNull() {
        var exp = assertThrows(NullPointerException.class, () -> EmployeeMapper.mapToEmployeeDto(null));
        assertEquals("Employee is null", exp.getMessage());
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inside the before all method");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Inside the before each method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Inside the after each method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Inside the after all method");
    }

}