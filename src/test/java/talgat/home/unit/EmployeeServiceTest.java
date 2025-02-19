package talgat.home.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import talgat.home.entity.Employee;
import talgat.home.repository.EmployeeRepository;
import talgat.home.service.EmployeeService;
import talgat.home.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();

        assert employee.getId() == null;
    }
}
